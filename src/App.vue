<template>
  <div>
    <!-- 顶部导航栏 -->
    <div class="topbar">
      <ul>
        <!-- 根据用户是否登录显示不同的内容 -->
        <li v-if="getUserName === ''" @click="login">登录</li>
        <li @click="toPage('info')" v-else>
          {{ getUserName }}
        </li>
        <!-- 导航链接 -->
        <li @click="toPage('home')">主页</li>
        <li @click="toPage('allProduct')">全部商品</li>
        <li @click="toPage('myOrder')">我的订单</li>
        <li :class="getNum > 0 ? 'shopCart-full' : 'shopCart'" @click="toPage('shoppingCart')" >
          <img class="img" src="./assets/shopping.png" v-if="getNum > 0"/>
          <img class="img" src="./assets/shopping1.png" v-else>
          购物车
          <span class="num">({{getNum}})</span>
        </li>
      </ul>
    </div>
    <!-- 顶端导航END -->
    <div style="width: 80%; margin-left: 10%;">
    <!-- 顶端容器 -->
      <div v-if="getUserName !== ''">
        <ul class="topViewUl">
          <li class="topViewLiLeft"><img src="./assets/logo.png"></li>
          <li class="topViewLiRight">
            <input class="topViewInput" type="text" placeholder = "请输入搜索内容" v-model.lazy="search" @keyup.enter="searchProduct  ">
            <button class="topViewButton" @click="searchProduct">搜索</button>
          </li>
        </ul>
      </div>
    </div>
      <!-- 顶端容器END -->
      <!-- 根据用户登录状态和当前窗口状态渲染不同的页面组件 -->
    <HelloWorld style="height: 92.5vh;" v-if="getUserName === ''" />
    <div style="width: 80%; margin-left: 10%;" v-else-if="getUserName !== ''" >
      <HomePageVue v-if="getWindow === 'home'" />
      <ShoppingCart v-else-if="getWindow === 'shoppingCart'" ></ShoppingCart>
      <MyOrderPageVue v-else-if="getWindow === 'myOrder'"/>
      <AllProductPageVue v-else-if="getWindow === 'allProduct'"/>
      <DetailPage v-else-if="getWindow === 'detail'"/>
      <UserInfoPageVue v-else-if="getWindow === 'info'"/>
    </div>
  </div>
  <div v-if="iSs"></div>
</template>

<script>
import ShoppingCart from './components/ShoppingCart.vue';
import MyOrderPageVue from './components/MyOrderPage.vue';
import HelloWorld from './components/HelloWorld.vue';
import HomePageVue from './components/HomePage.vue';
import {mapState, mapGetters } from 'vuex';
import AllProductPageVue from './components/AllProductPage.vue';
import DetailPage from './components/DetailPage.vue';
import UserInfoPageVue from './components/UserInfoPage.vue';
import axios from 'axios';

export default {
  data() {
    return {
      //该页面变量
      search:'',//搜索框内容
    };
  },
  components: {
    // 加载组件
    HelloWorld,
    HomePageVue,
    ShoppingCart,
    MyOrderPageVue,
    AllProductPageVue,
    DetailPage,
    UserInfoPageVue,
  },
  computed: {
    ...mapState(['userName']), // 将 Vuex 中的 state.userName 映射到组件的 userName
    ...mapGetters(['getUserName', 'getNum', 'getSearch', 'getPid', 'getWindow']), // 将 Vuex 中的 getter 映射到组件的计算属性中
    iSs(){
      if(this.getUserName !== ''){
        this.updateNum();
      }
      return this.getUserName !== '';
    }
  },
  mounted() {

  },
  methods: {
    toPage(window) {
      if(this.getUserName === ''){
        alert("请先登录！")
      }else{
        this.$store.commit('updateWindow', window);
        this.$store.commit('updateSearch', '');
        this.$store.commit('updatePid', '');
      }
    },
    searchProduct(){
      this.$store.commit('updateSearch', this.search);
      this.$store.commit('updateWindow', 'allProduct');
      this.search = '';
    },
    async updateNum(){
      if(this.getUserName !== ''){
        const num = (await axios.get('/api//getSumOfSNumByUid?uname=' + this.getUserName)).data;
        this.$store.commit('updateNum', num);
      }
    },
  },
};
</script>

<style scoped>
/* scoped 表示此样式只在当前组件中生效 */
ul{
  list-style-type: none;
}

/* 顶部导航栏CSS */
.topbar {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 40px;
  background-color: #3d3d3d;
}

.topbar ul {
  float: right;
  height: 100%;
  padding: 0 10%;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.topbar li {
    height: 100%;
    color: #b0b0b0;
    font-size: 14px;
    margin-left: 20px;
    display: flex;
    justify-content: center;
    align-items: center; 
}
/* 购物车空 */
.topbar  .shopCart {
  width: 120px;
}
/* 鼠标悬停在购物车上 */
.topbar  .shopCart:hover {
  background: #fff;
}
.topbar  .shopCart-full:hover {
  background: #fff;
}
/* 购物车内有物品 */
.topbar  .shopCart-full {
  width: 120px;
  color: #ff6700;
}
/* 购物车图片 */
.topbar  .img{
  width: 14px;
  height: 14px;
}
/* 顶部导航栏CSS END */
/* 顶部容器 */
.topViewUl {
  list-style: none; /* 移除列表样式 */
  padding: 20px 0 0 0;
  margin: 0;
  overflow: hidden; /* 清除浮动 */
}
.topViewLiLeft{
  float: left; /* 左浮动使得第一个 li 靠左 */
}
.topViewLiRight {
  float: right;
  margin-top: 10px;
}

.topViewInput {
  width: 200px;
  height: 20px;
  padding: 8px; /* 内边距 */
  border: 1px solid #ccc; /* 边框 */
  border-radius: 5px; /* 圆角 */
  font-size: 14px; /* 字体大小 */
}

.topViewButton {
  height: 37.6px;
  padding: 8px 16px; /* 内边距 */
  border: none; /* 移除按钮边框 */
  border-radius: 5px; /* 圆角 */
  background-color: #007bff; /* 背景颜色 */
  color: #fff; /* 文字颜色 */
  font-size: 14px; /* 字体大小 */
  cursor: pointer; /* 鼠标样式 */
  margin-left: 2px;
}

.topViewButton:hover {
  background-color: #0056b3; /* 悬停时背景颜色 */
}

/* 顶部容器END */
/* 一条线 */
.container {
  width: 100%;
  border-bottom: 1px solid #ccc; /* 添加下方的一条线 */
}
/* 线END */
</style>
