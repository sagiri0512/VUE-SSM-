    <template>
        <div style="margin-top: 10px">
            <!-- 轮播图 -->
            <div class="block">
                <el-carousel height="460px">
                    <el-carousel-item v-for="item in carousel" :key="item.cid">
                        <img style=" width: 100%;" :src="item.curl" />
                    </el-carousel-item>
                </el-carousel>
            </div>
            <!-- 轮播图END -->
            <!-- 商品展示区域 -->
            <p class="product-p">热销</p>
            <div class="product-list">
                <ul v-for="(product, index) of productList" :key="product.pid" :class="{ 'new-row': index % 4 === 0 && index !== 0 }">
                    <li @click="setPID(product.pid)">
                        <img :src="product.pimg" alt="Product Image" style="width: 80%;">
                        <p style="font-size: 30px;">{{ product.pname }}</p>
                        <p class="product-price">价格:¥{{ product.pprice }}</p>
                        <p class="product-pInventory">库存:{{ product.pinventory }}</p>
                    </li>
                </ul>
            </div>    
            <!-- 商品展示区域END -->
        </div>
    </template>

    <script>
    import axios from 'axios';
        export default{
            data(){
                return{
                    carousel: "",// 轮播图数据
                    productList: "", // 商品列表
                }
            },
            mounted(){
                this.getCarousel();
                this.getProduct(); 
            },
            computed: {
                displayedProducts() {
                    return this.productList.slice(0, this.maxProductsToShow);
                }
            },
            
            methods:{
                setPID(pid){
                    this.$store.commit('updatePid', pid);
                    this.$store.commit('updateWindow', 'detail');
                },
                async getCarousel() {
                    try {
                        const response = await axios.get('/api/getCarousel');
                        // 从响应中获取 Carousel 列表数据
                        this.carousel = response.data;
                    } catch (error) {
                        console.error('Error fetching carousel data:', error);
                    }
                }, 
                async getProduct(){
                    const response = await axios.get('/api/getTopProduct');
                    this.productList = response.data;
                },
            }
        }

    </script>

    <style scoped>
        ul{
            list-style-type: none;
        }
        /* 轮播图CSS */
        .block {
            margin: 0 auto;
        }
        /* 轮播图CSS END */
        /* 商品展示 */
    .product-p {
        background-color: #ff6347; /* 使用橙红色作为背景色 */
        color: white; /* 文字颜色为白色 */
        font-size: 36px; /* 字体大小为36像素 */
        text-align: center; /* 文字居中对齐 */
        padding: 10px 20px; /* 设置内边距，增加文字和边框的间距 */
        margin-top: 20px; /* 上边距增加至20像素 */
        border-radius: 10px; /* 设置圆角为10像素 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* 添加浅阴影效果 */
        width: fit-content; /* 根据内容自动调整宽度 */
        margin-left: auto; /* 水平居中 */
        margin-right: auto; /* 水平居中 */
        width: 96.5%;
    }

    .product-pInventory {
        font-size: 15px;
        text-align: right;
    }
    .product-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: center; /* 主轴居中对齐 */
        gap: 20px; /* 设置项目间的间隔为20像素 */
    }

    .product-list ul {
        flex: 0 0 calc(25% - 40px); /* 每行四个li，每个li占据25%宽度，考虑间隔宽度 */
        max-width: calc(25% - 40px); /* 每个 li 元素的最大宽度，考虑间隔宽度 */
        list-style: none;
        padding: 20px; /* 设置内边距 */
        text-align: center;
        border: 1px solid #ddd; /* 添加边框 */
        border-radius: 10px; /* 设置圆角 */
        background-color: #f9f9f9; /* 设置背景色 */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加浅阴影效果 */
        transition: transform 0.3s ease; /* 添加过渡效果 */
    }

    .product-list ul:hover {
        transform: translateY(-5px); /* 悬停时向上移动5像素 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* 悬停时添加更深的阴影效果 */
    }

    .product-list ul img {
        width: 100%; /* 图片宽度100%填充 */
    }

    .product-price {
        color: green;
        font-size: 25px;
        text-align: left;
        margin-top: 10px;
    }
    /* 商品展示END */

    </style>