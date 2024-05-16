<template>
  <div class="container">
    <div class="section-title">
      <h3>确认收货地址</h3>
    </div>
    <div
      v-for="(item, index) in AddressList"
      :key="item.aid"
      class="address-item"
      :class="{ 'larger-div': index === 0 }"
      style="cursor: pointer;"
    >
      <div class="radio-container">
        <input
          type="radio"
          :value="item"
          v-model="selectedAddress"
          @change="getDefalutadd"
          class="radio"
        />
      </div>
      <div class="address-details">
        <div>{{ item.atext }}</div>
      <div>({{ item.aname }}收)</div>
      <div>{{ item.aphonenNmber }}</div>
      </div>
      <div @click="getDid(item)" class="default-address">
        {{ item.did === 1 ? '默认地址' : '设为默认地址' }}
      </div>
    </div>

    <div class="section-title">
      <h3>确认订单信息</h3>
    </div>
    <div v-for="item in ProductInfo" :key="item.pid" class="table-row">
      <div class="table-cell">
        <img :src="item[0].pImg" class="productImg" />
      </div>
      <div class="table-cell">
        <div class="name">{{ item[0].pName }}</div>
      </div>
      <div class="table-cell price">￥{{ item[0].pPrice }}</div>
      <div class="table-cell">
        <div class="quantity">{{ item[0].sNum }}</div>
      </div>
      <div class="table-cell price">￥{{ item[0].pPrice * item[0].sNum }}</div>
    </div>

    <div class="footer">
      <div class="total-price">总价: ￥{{ getTotalPrice }}</div>
      <button @click="Settlement()" class="settlement-button">结算</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      AddressList: [],
      selectedAddress: null,
      ProductInfo: [],
    };
  },
  mounted() {
    this.getAddressList();
    this.getProductInfo();
  },
  computed: {
    ...mapGetters(["getUserName", "getSelectedPids", "getNum"]),
    getTotalPrice() {
      return this.ProductInfo.reduce((total, item) => total + item[0].pPrice * item[0].sNum, 0);
    },
    getTotalNum() {
      return this.ProductInfo.reduce((total, item) => total + item[0].sNum, 0);
    }
  },
  methods: {
    async getAddressList() {
      try {
        const defaultResponse = await axios.get(`/api/selectAddress?uname=${this.getUserName}&did=1`);
        const remainingResponse = await axios.get(`/api/selectAddress?uname=${this.getUserName}&did=0`);
        this.AddressList = [...defaultResponse.data, ...remainingResponse.data];
        if (!this.selectedAddress && this.AddressList.length > 0) {
          this.selectedAddress = this.AddressList[0];
        }
      } catch (error) {
        console.error("获取地址时出错", error);
      }
    },
    getDefalutadd() {
      const selectAddressItem = this.selectedAddress;
      const confirmResult = confirm("确认要更换收货地址吗?");
      if (confirmResult) {
        this.AddressList = [selectAddressItem];
      } else {
        this.selectedAddress = this.AddressList[0];
      }
    },
    async getProductInfo() {
      try {
        for (const pid of this.getSelectedPids) {
          const response = await axios.get(`/api/selectShoppingCartById?uname=${this.getUserName}&pid=${pid}`);
          this.ProductInfo.push(response.data);
        }
      } catch (error) {
        console.error("获取订单信息出错", error);
      }
    },
    async Settlement() {
      this.$store.commit("updateWindow", "myOrder");
      if (this.selectedAddress) {
        for (let i = 0; i < this.ProductInfo.length; i++) {
          const payload = {
            uname: this.getUserName,
            aid: this.selectedAddress.aid,
            pid: this.ProductInfo[i][0].pid,
            pPice: this.ProductInfo[i][0].pPrice,
            sNum: this.ProductInfo[i][0].sNum,
          };
          const response = await axios.post("/api/addPayment", payload);
          if(response.data !== "success"){
            alert(response.data.name + "库存不足，已购买" + response.data.sum + "，该商品应支付" + response.data.sum*this.ProductInfo[i][0].pPrice + "元");
          }
        }
        for (const item of this.ProductInfo) {
          try {
            await axios.get("/api/deleteShoppingCart", { params: { uname: this.getUserName, pid: item[0].pid } });
            console.log(this.getTotalNum);
            this.$store.commit("updateNum", this.getNum - this.getTotalNum);
          } catch (error) {
            console.error("删除购物车商品时出错:", error);
          }
        }
      } else {
        alert("请选择收货地址");
      }
    },
    async getDid(item) {
      if (item.did === 1) return;
      await axios.get('/api/clearAll');
      await axios.get(`/api/setDid?aid=${item.aid}`);
      this.getAddressList();
    },
  },
};
</script>

<style scoped>
.container {
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
}

.section-title {
  margin-bottom: 15px;
}

.address-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.larger-div {
  background-color: #e0f7fa;
  font-size: 18px;
}

.radio-container {
  margin-right: 15px;
}

.address-details {
  flex: 1;
}

.default-address {
  margin-left: 15px;
  color: #007bff;
  cursor: pointer;
}

.default-address:hover {
  text-decoration: underline;
}

.table-row {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-bottom: 10px;
}

.table-cell {
  flex: 1;
  text-align: center;
}

.productImg {
  width: 80px;
  height: 80px;
  object-fit: cover;
}

.name {
  font-weight: bold;
}

.price {
  color: #ffa500;
}

.footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-top: 20px;
}

.total-price {
  font-size: 20px;
  font-weight: bold;
}

.settlement-button {
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.settlement-button:hover {
  background-color: #218838;
}
</style>
