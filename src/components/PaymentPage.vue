<template>
  <div><h3>确认收货地址</h3></div>
  <div
    v-for="(item, index) in AddressList"
    :key="item.aid"
    class="address-item"
    :class="{ 'address-item': true, 'larger-div': index === 0 }"
    
    style="cursor: pointer;" 
  >
    <div>
      <input
        type="radio"
        :value="item"
        v-model="selectedAddress"
        @change="getDefalutadd"
        class="radio"
      />
    </div>
    <div>
      <div>{{ item.atext }}</div>
      <div>({{ item.aname }}收)</div>
      <div>{{ item.aphonenNmber }}</div>
    </div>
    <div @click="getDid(item)">
      {{ item.did === 1 ? '默认地址' : '设为默认地址' }}
  </div>
  </div>
  <div><h3>确认订单信息</h3></div>
  <!-- 循环根据获得的值对div进行填充 -->
  <div v-for="item in ProductInfo" :key="item.pid" class="table-row">
    <div class="table-cell">
      <img :src="item[0].pImg" class="productImg" />
    </div>
    <div class="table-cell">
      <div class="Name">{{ item[0].pName }}</div>
    </div>
    <div class="table-cell" style="color: #ffa500">￥{{ item[0].pPrice }}</div>
    <div class="table-cell">
      <div class="table-cell">{{ item[0].sNum }}</div>
    </div>
    <div class="table-cell" style="color: #ffa500">
      ￥{{ item[0].pPrice * item[0].sNum }}
    </div>
  </div>
  <!-- 尾部 -->
  <div>
    <div>{{ getTotalPrice }}</div>
    <button @click="Settlement()">结算</button>
  </div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      AddressList: [],
      selectedAddress: null, // 初始化为 null
      ProductInfo: [],
    };
  },
  mounted() {
    this.getAddressList();
    this.getProductInfo(); // 在此添加这一行来获取订单信息
  },
  computed: {
    ...mapGetters(["getUserName", "getSelectedPids", "getNum"]), // 将 getSelectedPids 映射到 computed 属性中
    //获取总价
    getTotalPrice() {
      let TotalPrice = 0;
      this.ProductInfo.forEach((item) => {
        TotalPrice += item[0].pPrice * item[0].sNum;
      });
      return TotalPrice;
    },
    getTotalNum(){
      let TotalNum = 0;
      this.ProductInfo.forEach(item=>{
        TotalNum+=item[0].sNum;
      });
      return TotalNum;
    }
  },
  methods: {
    /* 获取收货地址 */
    async getAddressList() {
      try {
        // 首先获取 did 为 1 的地址
        const defaultResponse = await axios.get(
          "/api/selectAddress?uname=" + this.getUserName + "&did=1"
        );
        
        // 再获取剩下的地址
        const remainingResponse = await axios.get(
          "/api/selectAddress?uname=" + this.getUserName + "&did=0"
        );
        // 将默认地址和剩余地址合并到 AddressList 中
        this.AddressList = [...defaultResponse.data, ...remainingResponse.data];
        // 如果没有选中地址，将第一个地址设置为选中
        if (!this.selectedAddress && this.AddressList.length > 0) {
          this.selectedAddress = this.AddressList[0];
        }
      } catch (error) {
        console.error("获取地址时出错", error);
      }
    },
    /* 选地址 */
    getDefalutadd() {
      // 找到被选中的单选框对应的对象
      const selectAddressItem = this.selectedAddress;
      // 弹出确认取消的提示框
      const confirmResult = confirm("确认要更换收货地址吗?");
      // 将其作为数组的唯一元素，覆盖AddressList
      if (confirmResult) {
        this.AddressList = [selectAddressItem];
      } else {
        this.selectedAddress = this.AddressList[0];
      }
    },
    /* 获取购物车页选中的数据 */
    async getProductInfo() {
      try {
        for (var i = 0; i < this.getSelectedPids.length; i++) {
          
          const pid = this.getSelectedPids[i];
          const response = await axios.get(
            "/api/selectShoppingCartById?uname=" +
              this.getUserName +
              "&pid=" +
              pid
          );
          this.ProductInfo.push(response.data);
        }
      } catch (error) {
        console.error("获取订单信息出错", error);
      }
    },
    /* 进行结算操作,传递pid,uid,aid*/
    async Settlement() {
      this.$store.commit("updateWindow", "myOrder");
      if (this.selectedAddress) {
        for (let i = 0; i < this.ProductInfo.length; i++) {
          const item = this.ProductInfo[i][0];
          const payload = {
            uname: this.getUserName,
            aid: this.selectedAddress.aid,
            pid: item.pid,
            pPice: item.pPrice,
            sNum: item.sNum,
          };
          const response = await axios.post("/api/addPayment", payload);
          if (response.data !== "success") {
            alert(response.data.name + "库存不足，该商品已购买" + response.data.sum + "，并已从购物车中删除！本商品需付款" + response.data.sum * payload.pPice + "元");
          }
        }
        for (let i = 0; i < this.ProductInfo.length; i++) {
          const item = this.ProductInfo[i][0];
          const pids = {
            pid: item.pid,
          };

          try {
            await axios.get("/api/deleteShoppingCart", {
              params: {
                uname: this.getUserName,
                pid: pids.pid,
              },
            });
             // 在成功删除购物车商品后更新购物车数量
             this.$store.commit("updateNum",this.getNum-this.getTotalNum)
          } catch (error) {
            console.error("删除购物车商品时出错:", error);
          }
        }
      } else {
        alert("请选择收货地址");
      }
    },
    /* 设置默认地址 */
    async getDid(item){
      console.log(item.did);
      console.log(item.aid);
      if(item.did===1){
        return;
      }else{
        axios.get('/api/clearAll');
        await axios.get('/api/setDid?aid='+item.aid);
        this.getAddressList();
      }
    },
  },
};
</script>

<style>
.address-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.larger-div {
  /* 添加样式以使第一个 div 大一些并具有颜色 */
  font-size: 18px;
}

.address-item input[type="checkbox"] {
  margin-right: 10px;
  transform: scale(1.2); /* 让复选框变大 */
}

.address-item div {
  padding: 5px 10px;
  border-radius: 5px;
  background-color: #f0f0f0;
}

.radio:hover {
  cursor: pointer;
}

.table-cell {
  flex: 1;
  border-bottom: 1px solid #ddd;
  padding: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.hover-effect {
  background-color: lightgray; /* 悬停时的背景颜色 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 悬停时的阴影效果 */
}
</style>