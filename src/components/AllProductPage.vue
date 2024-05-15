<template>
  <div>
    <!-- 分类栏 -->
    <div class="horizontal-list-container">
      <ul class="horizontal-list">
        <li>分类:</li>
        <li
          :class="sType === '全部' ? 'sType' : null"
          @click="
            toType('全部');
            getProduct(0);
          "
        >
          全部
        </li>
        <li
          v-for="item of typeList"
          :key="item.tid"
          :class="sType === item.tname ? 'sType' : null"
          @click="
            toType(item.tname);
            getProduct(item.tid);
          "
        >
          {{ item.tname }}
        </li>
      </ul>
    </div>
    <!-- 分类栏END -->
    <!-- 商品展示区域 -->
    <div class="product-list">
      <ul
        v-for="(product, index) of productList"
        :key="product.pid"
        :class="{ 'new-row': index % 4 === 0 && index !== 0 }"
      >
        <li @click="setPID(product.pid)">
          <img :src="product.pimg" alt="Product Image" style="width: 80%" />
          <p style="font-size: 30px">{{ product.pname }}</p>
          <p class="product-price">价格:¥{{ product.pprice }}</p>
        </li>
      </ul>
    </div>
    <!-- 商品展示区域END -->
  </div>
  <div v-if="productList.length === 0" class="no-product-message">
    没有找到任何商品哦！
  </div>

  <div v-if="isSearchEmpty"></div>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["getSearch", "getPid"]), // 将 Vuex 中的 getter 映射到组件的计算属性中
    isSearchEmpty() {
      if (this.getSearch !== "") {
        this.getProduct();
      }
      return this.getSearch === "";
    },
  },
  data() {
    return {
      typeList: [],
      sType: "全部", //选中的类型
      productList: "", // 商品列表
    };
  },
  mounted() {
    this.getType();
    this.getProduct(0);
  },
  methods: {
    setPID(pid) {
      this.$store.commit("updatePid", pid);
      this.$store.commit("updateWindow", "detail");
    },
    async getType() {
      const resp = await axios.get("/api/getType");
      this.typeList = resp.data;
    },
    toType(type) {
      this.sType = type;
      this.$store.commit("updateSearch", "");
    },
    async getProduct(tid) {
      if (this.getSearch === "") {
        if (this.sType === "全部") {
          const response = await axios.get("/api/getProduct");
          this.productList = response.data;
        } else {
          const response = await axios.get("/api/getProductByTid?tid=" + tid);
          this.productList = response.data;
        }
      } else {
        this.sType = "";
        const keyword = this.getSearch;
        const response = await axios.get(
          "/api/getProductByKeyword?keyword=" + keyword
        );
        this.productList = response.data;
        console.log(this.productList);
      }
    },
  },
};
</script>

<style>
ul {
  list-style-type: none;
}
/* 分类栏容器 */
.horizontal-list-container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 10px 0; /* 上下边距 */
  background-color: #f5f5f5; /* 背景色 */
  border-radius: 5px; /* 圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

/* 分类列表 */
.horizontal-list {
  list-style: none;
  display: flex;
  flex-wrap: nowrap;
  padding: 0;
  margin: 0;
}

/* 列表项样式 */
.horizontal-list li {
  font-size: 18px;
  margin-right: 15px; /* 调整列表项间距 */
  cursor: pointer; /* 添加指针样式 */
  transition: color 0.3s ease; /* 添加颜色过渡效果 */
}

/* 选中样式 */
.horizontal-list li.sType {
  color: orange;
  font-weight: bold; /* 加粗文字 */
}

/* 悬停效果 */
.horizontal-list li:hover {
  color: #ff6347; /* 悬停时文字颜色变化 */
}
/* 分类栏END */
/* 商品展示 */
.product-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start; /* 从左边开始排列 */
  gap: 20px; /* 设置项目间的间隔为20像素 */
}

.product-list ul {
  flex: 0 0 calc(25% - 20px); /* 每行四个li，每个li占据固定的 25% 宽度，考虑间隔宽度 */
  max-width: calc(25% - 20px); /* 每个 li 元素的最大宽度，考虑间隔宽度 */
  list-style: none;
  padding: 20px; /* 设置内边距 */
  text-align: center;
  box-sizing: border-box; /* 计算包括 padding 和 border 在内的盒模型 */
  border: 1px solid #ddd; /* 添加边框 */
  border-radius: 10px; /* 设置圆角 */
  background-color: #f9f9f9; /* 设置背景色 */
  transition: transform 0.3s ease-in-out; /* 添加过渡效果 */
}

.product-list ul:hover {
  transform: translateY(-5px); /* 悬停时向上移动5像素 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加悬停时的阴影效果 */
}

.product-list ul img {
  width: 100%; /* 图片宽度100%填充 */
}

.product-price {
  color: green;
  font-size: 25px;
  text-align: left;
  margin-left: 10px; /* 调整价格文字左边距 */
  margin-top: 10px;
}
/* 商品展示END */
/* 没有商品消息样式 */
.no-product-message {
  font-size: 24px;
  margin-top: 20px;
  color: red;
  text-align: center;
  padding: 20px;
  background-color: #f8d7da; /* 设置背景色为浅红色 */
  border: 1px solid #f5c6cb; /* 添加红色边框 */
  border-radius: 5px; /* 设置圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加浅阴影效果 */
}
</style>