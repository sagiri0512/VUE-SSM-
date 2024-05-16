<template>
    <div class="container1">
        <h3>我的订单</h3>
    </div>
    <div class="table-row1">
        <div class="table-cell1">商品编号</div>
        <div class="table-cell1">商品图片</div>
        <div class="table-cell1">商品名称</div>
        <div class="table-cell1 address-cell2">收货地址</div>
        <div class="table-cell1">购买数量</div>
        <div class="table-cell1">购买总价</div>
        <div class="table-cell1">收货状态</div>
    </div>
    <div v-for="(item, index) in orders" :key="item.paid" class="table-row1">
        <div class="table-cell1">{{ item.paid }}</div>
        <div class="table-cell1"><img :src="item.pImg" class="productImg"></div>
        <div class="table-cell1">{{ item.pname }}</div>
        <div class="table-cell1 address-cell1">
            <div>收货人:{{ item.aname }}</div>
            <div>收货地址:{{ item.atext }}</div>
            <div>联系方式:{{ item.aphonenumber }}</div>
        </div>
        <div class="table-cell1">{{ item.number }}个</div>
        <div class="table-cell1">￥{{ item.payPrice }}</div>
        <div class="table-cell1"><button class="confirm-button" @click="changestatus(item, index)">{{Receivingstatus[item.state]}}</button></div>
    </div>
    <div v-if="Is"></div>
</template>

<script>
import axios from 'axios';
import { mapGetters } from 'vuex';
export default{
    data(){
        return{
            orders:[],
            Receivingstatus:['未收货','已收货'],
        }
    },
    mounted(){
        this.getOrder();
    },
    computed:{
        ...mapGetters(["getUserName", "getWindow", "getNum"]),
        Is(){
            this.getOrder();
            return this.getNum;
        }
    },
    methods:{
        //获取订单信息
        async getOrder(){
            try{
                const response = await axios.get('/api/getAllOrder?uname='+this.getUserName);
                this.orders = response.data;
                console.log(response.data);
            }catch(error){
                console.error('获取订单出错',error)
            }
        },
        //点击确认收货
        changestatus(item, index){
            if(item.state===1){
                return;
            }else{
                const confirmResult = confirm('确认收货吗?');
                if(confirmResult){
                    try{
                    axios.get('/api/updateState?paid='+item.paid);
                    this.orders[index].state = 1;
                    }catch(error){
                        console.error('更改状态错误',error)
                    }
                }
            }
        },
    },
}
</script>

<style scoped>
.container1 {
  margin-top: 20px;
}

.table-row1 {
  display: flex;
}

.table-cell1 {
    display: flex;
    flex-direction: column;
    flex: 1;
    border-bottom: 1px solid #ddd;
    padding: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.productImg {
  border-radius: 5px; /* 圆角边框 */
  box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.1); /* 阴影效果 */
  width: 40%;
}

.address-cell1 {
    flex: 2; /* 让收货地址单元格占据更多的空间 */
    align-items: start;
    color: #ffa500;
    padding-left: 5%;
}

.address-cell2{
    flex: 2;
}
.confirm-button {
  background-color: #ffa500; /* 按钮背景颜色 */
  color: #fff; /* 按钮文本颜色 */
  border: none; /* 移除按钮边框 */
  border-radius: 4px; /* 圆角边框 */
  padding: 8px 16px; /* 按钮内边距 */
  cursor: pointer; /* 鼠标样式为手型 */
  transition: background-color 0.3s; /* 添加过渡效果 */
}


</style>