<template>
  <div>
    <div class="contanier">
      <!-- div头部 -->
      <div class="table">
        <div class="table-row header">
          <div class="table-cell">
            <input
              type="checkbox"
              @click="Allcheckbox()"
              v-bind:checked="isAllchecked"
              @change="getInfo()"
            />全选
          </div>
          <div class="table-cell">商品图片</div>
          <div class="table-cell">商品名称</div>
          <div class="table-cell">单价</div>
          <div class="table-cell">数量</div>
          <div class="table-cell">总价</div>
          <div class="table-cell">操作</div>
        </div>
        <!-- 循环根据获得的值对div进行填充 -->
        <div v-for="item in shoppingList" :key="item.pid" class="table-row">
          <div class="table-cell">
            <input
              type="checkbox"
              style="margin-left: -28px"
              v-model="item.isChecked"
              @change="Checkbox(), getInfo()"
            />
          </div>
          <div class="table-cell">
            <img
              :src="item.pImg"
              class="productImg"
              @click="toPage(item.pid)"
            />
          </div>
          <div class="table-cell" @click="toPage(item.pid)">
            <div class="Name">{{ item.pName }}</div>
          </div>
          <div class="table-cell" style="color: #ffa500">
            ￥{{ item.pPrice }}
          </div>
          <div class="table-cell">
            <button
              class="btn transparent-btn"
              @click.prevent="decrement(item)"
            >
              -
            </button>
            <div class="table-cell">{{ item.sNum }}</div>
            <button
              class="btn transparent-btn"
              @click.prevent="increment(item)"
            >
              +
            </button>
          </div>
          <div class="table-cell" style="color: #ffa500">
            ￥{{ item.pPrice * item.sNum }}
          </div>
          <div class="table-cell">
            <button
              class="delete-btn"
              @click="delectProduct(item.pid, item.uid, item.sNum)"
            >
              ×
            </button>
          </div>
        </div>
        <!-- 表尾 -->
        <div class="table-row footer">
          <div class="table-cell">
            <div
              class="allproduct"
              style="color: #ffa500"
              @click="toPageProduct()"
            >
              继续购物
            </div>
          </div>
          <div class="table-cell">
            已选择<span style="color: #ffa500">{{ totalNum }}</span
            >件
          </div>
          <div class="table-cell"></div>
          <div class="table-cell"></div>
          <div class="table-cell"></div>
          <div class="table-cell">总价:￥{{ totalPrice }}</div>
          <div class="table-cell">
            <button class="checkout-btn" @click="toPagePayment()">
              去结账
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import axios from "axios";
import { mapGetters } from "vuex";
export default {
  data() {
    return {
      isAllchecked: false, // 默认不全选
      shoppingList: [],
    };
  },
  mounted() {
    this.getShoppingList();
  },
  computed: {
    ...mapGetters(["getUserName", "getPid", "getNum"]),
    totalPrice() {
      if (this.getInfo() != undefined) {
        //获取getInfo中的select数组
        const getInfo = this.getInfo();
        let totalPrice = 0;
        getInfo.forEach((item) => {
          totalPrice += item.price * item.num;
        });
        return totalPrice;
      } else {
        return 0;
      }
    },
    totalNum() {
      if (this.getInfo() != undefined) {
        const getInfo = this.getInfo();
        let totalNum = 0;
        getInfo.forEach((item) => {
          totalNum += item.num;
        });
        return totalNum;
      } else {
        return 0;
      }
    },
  },
  methods: {
    /* 获取购物车的内容 */
    async getShoppingList() {
      try {
        const response = await axios.get(
          "/api/getShoppingCartByUid?uname=" + this.getUserName
        );
        this.shoppingList = response.data;
      } catch (error) {
        console.error("获取购物车详情时出错", error);
      }
    },
    /* 跳转页面 */
    toPage(pid) {
      this.$store.commit("updatePid", pid);
      this.$store.commit("updateWindow", "detail");
    },
    toPageProduct() {
      this.$store.commit("updateWindow", "allProduct");
    },
    /* 删除操作 */
    async delectProduct(pid, uid, sNum) {
      const confirmed = confirm("确定要删除吗？"); // 弹出确认框
      if (confirmed) {
        try {
          await axios.get(
            "/api/deleteShoppingCartById?uid=" + uid + "&pid=" + pid
          );
          const num = this.getNum - sNum;
          this.$store.commit("updateNum", num);
          this.getShoppingList();
        } catch (error) {
          console.error("删除时出错", error);
        }
      }
    },
    /* 对数量增减 */
    async increment(item) {
      try {
        const num = this.getNum + 1;
        this.$store.commit("updateNum", num);
        const index = this.shoppingList.indexOf(item); // 找到项目的索引
        if (index !== -1) {
          // 如果在数组中找到项目
          item.sNum++;
          // 使用 Vue.set 确保响应性
          this.shoppingList[index] = {
            ...this.shoppingList[index],
            sNum: item.sNum,
          };
          console.log(item.sNum);
          await axios.get(
            "/api//updateNum?uid=" +
              item.uid +
              "&pid=" +
              item.pid +
              "&sNum=" +
              item.sNum
          );
        }
      } catch (error) {
        console.error("添加失败", error);
      }
    },
    async decrement(item) {
      try {
        if (item.sNum <= 1) {
          alert("商品数量不能小于1");
          return;
        }
        const num = this.getNum - 1;
        this.$store.commit("updateNum", num);
        item.sNum--;
        await axios.get(
          "/api//updateNum?uid=" +
            item.uid +
            "&pid=" +
            item.pid +
            "&sNum=" +
            item.sNum
        );
      } catch (error) {
        console.error("添加失败", error);
      }
    },
    /* 全选框将所有单选框选中 */
    Allcheckbox() {
      const isChecked = event.target.checked;
      this.shoppingList.forEach((item) => {
        item.isChecked = isChecked;
      });
      this.isAllchecked = isChecked;
    },
    /* 根据勾选的复选框来计算总价 */
    Checkbox() {
      let anyUnchecked = this.shoppingList.some((item) => !item.isChecked);
      this.isAllchecked = !anyUnchecked;
    },
    /* 获取被选中的商品的价格和数量 */
    getInfo() {
      if (Array.isArray(this.shoppingList)) {
        let select = [];
        this.shoppingList.forEach((item) => {
          if (item.isChecked) {
            select.push({
              price: item.pPrice,
              num: item.sNum,
            });
          }
        });
        return select;
      } else {
        console.error("购物车里没有东西");
      }
    },
    /* 跳转到结算页面 传递被选中的商品的id*/
    async toPagePayment() {
      try {
        // 创建一个空数组来存储被选中商品的pid
        const selectedPids = [];
        // 遍历购物车列表
        this.shoppingList.forEach((item) => {
          // 如果商品被选中，则将其pid添加到数组中
          if (item.isChecked) {
            selectedPids.push(item.pid);
          }
        });
        //判断是否勾选了商品
        if (this.totalPrice != 0) {
          this.$store.commit("setSelectedPids", selectedPids);
          this.$store.commit("updateWindow", "payment");
        } else {
          alert("请勾选商品!");
        }
      } catch (error) {
        console.error("添加出现问题", error);
      }
    },
  },
};
</script>
  
<style scoped>
.container {
  margin-top: 20px;
}

.table {
  width: 100%;
}

.table-row {
  display: flex;
}

.table-cell {
  flex: 1;
  border-bottom: 1px solid #ddd;
  padding: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.table-row.header .table-cell {
  background-color: #f2f2f2;
}

.transparent-btn {
  background-color: transparent;
  color: #777;
  border: 1px solid #777;
  padding: 5px 10px;
  cursor: pointer;
  transition: color 0.3s, border-color 0.3s;
}

.transparent-btn:hover {
  color: #333;
  border-color: #333;
}

.count-input {
  border: 1px solid #ddd;
  padding: 6px;
  margin: 0 4px;
  width: 60px;
  text-align: center;
}

.delete-btn {
  background-color: transparent;
  border: 0px solid #ccc;
  border-radius: 50px;
  padding: 5px 10px;
  cursor: pointer;
}

.delete-btn:hover {
  background-color: #ffa500;
}

.productImg {
  border-radius: 5px; /* 圆角边框 */
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  width: 40%;
}

.productImg:hover {
  /* 添加鼠标悬停时的样式 */
  /* 例如改变图片的透明度 */
  opacity: 0.8;
  /* 或者添加阴影 */
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
  /* 或者缩放图片 */
  transform: scale(1.05);
  /* 或者任何你想要的样式 */
  cursor: pointer;
}

.Name:hover {
  cursor: pointer;
}

.allproduct:hover {
  cursor: pointer;
}

.checkout-btn {
  margin-left: 15%;
  width: 130px;
  height: 45px;
  background-color: #ffa500; /* 按钮背景颜色 */
  color: #fff; /* 按钮文本颜色 */
  border: none; /* 移除按钮边框 */
  border-radius: 4px; /* 圆角边框 */
  cursor: pointer; /* 鼠标样式为手型 */
  transition: background-color 0.3s; /* 添加过渡效果 */
}

.checkout-btn:hover {
  background-color: #ff5100; /* 鼠标悬停时改变按钮背景颜色 */
}
</style>
  
  