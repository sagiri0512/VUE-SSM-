<template>
  <div class="product-details-container">
    <!-- 商品展示轮播图 -->
    <div class="carousel-container" v-if="product">
      <el-carousel indicator-position="outside">
        <el-carousel-item v-for="detail in detailList" :key="detail.did">
          <img :src="detail.dimg" class="carousel-image" />
        </el-carousel-item>
      </el-carousel>
    </div>

    <!-- 商品介绍 -->
    <div class="product-info" v-if="product">
      <ul>
        <li ><strong class="product-name">商品名</strong><p style="text-align: center; font-size: 20px; color: orange;font-weight: bold;">{{ product && product.pname }}</p></li>
        <li class="product-introduce"><strong>商品介绍</strong><br>{{ product && product.pintroduce }}</li>
        <li style="color: green; font-size: 30px; margin-top: 40px;">¥{{ product && product.pprice ? product.pprice.toFixed(2) : '0.00' }}</li>
      </ul>
      <button @click="addToCart" class="add-to-cart-btn">加入购物车</button>
    </div>

    <!-- 加载或错误信息 -->
    <div v-else>
      <p>正在加载商品详情...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';

export default {
  data() {
    return {
      detailList: [],
      product: null,
    };
  },
  mounted() {
    this.getDetailList();
    this.getProduct();
  },
  computed: {
    ...mapGetters(['getPid', 'getUserName', 'getNum']),
  },
  methods: {
    async getDetailList() {
      try {
        const response = await axios.get('/api/getDetailByPID?pid=' + this.getPid);
        this.detailList = response.data;
      } catch (error) {
        console.error('获取商品详情列表时出错:', error);
      }
    },
    async getProduct() {
      try {
        const response = await axios.get('/api/getProductByPid?pid=' + this.getPid);
        console.log(response.data);
        this.product = response.data;
      } catch (error) {
        console.error('获取商品信息时出错:', error);
      }
    },
    async addToCart() {
      try{
          if((await axios.get('/api/addShoppingCart?uname=' + this.getUserName + '&pid=' + this.product.pid)).data === "success"){
              const num = this.getNum + 1;
              this.$store.commit('updateNum', num);
          }else{
              alert('添加失败');
          }
      } catch(error){
          console.error('添加购物车失败:', error);
      }
    },
  },
};
</script>

<style scoped>
.product-details-container {
  display: flex;
  justify-content: space-between;
  margin: 10px;
}

.carousel-container {
  width: 40%; /* 根据需要调整宽度 */
  margin: 8% 0 0 0;
  
}

.carousel-image {
  width: 100%;
  height: auto;
}

.product-info {
  width: 50%; /* 根据需要调整宽度 */
}

.product-info ul {
  list-style: none;
  padding: 0;
}

.product-info li {
  margin-bottom: 10px;
}

.add-to-cart-btn {
  margin-top: 30px;
  display: block;
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-to-cart-btn:hover {
  background-color: #0056b3;
}

.product-name {
  text-align: center;
  font-size: 24px;
  color: black;
  background-color: #ddd;
  padding: 10px;
  display: block;
  width: 100%; /* 设置宽度为100% */
  box-sizing: border-box; /* 盒模型设置为边框盒，包括 padding 和 border 在内 */
  border-radius: 5px; /* 圆角 */
}

.product-introduce {
  font-size: 16px;
  color: black;
  background-color: #ddd;
  padding: 10px;
  display: block;
  width: 100%; /* 设置宽度为100% */
  height: 180px; /* 设置固定高度 */
  overflow: hidden; /* 溢出内容隐藏 */
  box-sizing: border-box; /* 盒模型设置为边框盒 */
  border-radius: 5px; /* 圆角 */
}
</style>