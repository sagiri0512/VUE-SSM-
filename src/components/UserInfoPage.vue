<template>
    <div id="body_div">
        <!-- 左侧导航栏 -->
        <div id="body_div_left">
            <!-- 头像 -->
            <div id="body_div_left_top">
                <img :src="UHeadImage">
                <text>{{ getUserName }}</text>
            </div>
            <div id="body_div_left_bottom">
                <div id="check_1" @click="toUserInfoWindow('secure')">登录与安全</div>
                <div id="check_2" @click="toUserInfoWindow('info')">个人信息</div>
                <div id="check_3" @click="toUserInfoWindow('address')">收货地址</div>
            </div>
        </div>
        <!-- 左侧导航END -->
        <!-- 右侧内容栏 -->
        <div id="body_div_right">
            <!-- 安全 -->
            <div v-if="UserInfoWindow === 'secure'" id="show_1">
                <div style="font-weight: bold;font-size: 20px;margin: 20px">|登录方式</div>
                <ul>
                    <li id="find_phone" @click="toUserFunction('phone')">
                        <div class="li_div"><img src="../assets/phone_ico.png" alt="" class="li_img">安全手机</div>
                        <div class="li_div">
                            <p v-if="User.uphoneNmber === null">暂未绑定</p>
                            <p v-else>{{ User.uphoneNmber }}</p>
                            <img src="../assets/Arrowright.png" alt="" class="li_img">
                        </div>
                    </li>
                    <li id="find_email" @click="toUserFunction('email')">
                        <div class="li_div"><img src="../assets/Mailbox_icon.png" alt="" class="li_img">安全邮箱</div>
                        <div class="li_div">
                            <p v-if="User.umailBox === null">暂未绑定</p>
                            <p v-else>{{ User.umailBox }}</p>
                            <img src="../assets/Arrowright.png" alt="" class="li_img">
                        </div>
                    </li>
                    <li id="modify_pwd" @click="toUserFunction('password')">
                        <div class="li_div"><img src="../assets/modify_PWD.png" alt="" class="li_img">修改密码</div>
                        <div class="li_div"><img src="../assets/Arrowright.png" alt="" class="li_img"></div>
                    </li>
                </ul>
            </div>
            <!-- 安全END -->
            <!-- 个人信息 -->
            <div v-if="UserInfoWindow === 'info'" id="show_2">
                <div style="font-weight: bold;font-size: 20px;margin: 20px">|个人信息</div>
                <div id="show_2_div">
                    <div class="show_2_div_body">
                        <text>头像</text>
                        <img :src="UHeadImage" id="headPath" alt="" style="width: 60px;height:60px;margin-right: 50px;border-radius: 50%">
                        <input type="file" name="file" id="file" v-if="User.uheadImage === null">
                    </div>
                    <div class="show_2_div_body">
                        <text>UID</text>
                        <text>{{ User.uid }}</text>
                    </div>
                    <div class="show_2_div_body">
                        <text>昵称</text>
                        <text id="Nickname" v-if="User.unickName === null">暂未设置</text>
                        <text id="Nickname" v-else>{{User.unickName}}</text>
                        <input type="text" id="nickname_input" v-if="User.unickName === null">
                    </div>
                    <div class="show_2_div_body">
                        <text>性别</text>
                        <text id="sex" v-if="User.usex == null">暂未设置</text>
                        <text id="sex" v-else-if="User.usex == 1">男</text>
                        <text id="sex" v-else-if="User.usex == 0">女</text>
                        <div v-if="User.usex === null">
                            <label for="sex_1"  id="label_1"> <input type="radio" id="sex_1" name="sex" value="1" checked >男</label>
                            <label for="sex_0"  id="label_0"> <input type="radio" id="sex_0" name="sex" value="0" >女</label>
                        </div>
                    </div>
                    <div class="show_2_div_body">
                        <text>年龄</text>
                        <text id="age" v-if="User.usex === null"> 暂未设置 </text>
                        <text id="age" v-else>{{User.usex}}</text>
                        <input type="text" id="age_input" v-if="User.usex === null" >
                    </div>
                </div>
                <div id="show_2_div_btn">
                    <button id="modify_info_btn"><span id="info_btn_text">修改个人信息</span></button>
                </div>
            </div>
            <!-- 个人信息END -->
            <!-- 收货地址 -->
            <div v-else-if="UserInfoWindow === 'address'" id="show_3">
                <div style="font-weight: bold;font-size: 20px;margin: 20px">|收货地址</div>
                <div id="shou_3_overflow_scroll">
                    <ul id="show_3_div" v-for="addres of address" :key="addres.aid">
                        <li>
                            姓名：{{ addres.aname }}<br> 手机：{{ addres.aphonenNmber }}<br> 地址：{{ addres.atext }}<br>
                            <div style="margin-top: 15px;">
                                <button>删除</button>
                                <button>修改</button><br>
                            </div>
                        </li>
                    </ul>
                </div>

                <button id="add_address_btn" class="add_address_btn_class" @click="toUserFunction('addSite')">添加地址</button>
            </div>
            <!-- 收货地址END -->
        </div>
    </div>
    <!-- 修改手机号 -->
    <div v-if="UserFunction === 'phone'" id="find_phone_div">
        <div @click="toUserFunction('')"><img src="../assets/close.png" class="close" id="close_phone_btn" ></div>
        <div id="find_phone_div_title">绑定安全手机</div>
        <div id="find_phone_div_content">
            <div>输入新手机</div>
            <input type="text" placeholder="请输入手机号" id="phone_input" v-model="newUPhoneNmber">
        </div>
        <button id="phone_btn" @click="phoneChange()">确定</button>
    </div>
    <!-- 修改手机号END -->
    <!-- 修改邮箱 -->
    <div v-else-if="UserFunction === 'email'" id="find_email_div">
        <div @click="toUserFunction('')"><img src="../assets/close.png" class="close" id="close_email_btn" ></div>
        <div id="find_email_div_title">绑定安全邮箱</div>
        <div id="find_email_div_content">
            <div>输入新邮箱</div>
            <input type="text" placeholder="请输入邮箱" id="email_input">
        </div>
        <button id="email_btn">确定</button>
    </div>
    <!-- 修改邮箱END -->
    <!-- 修改密码 -->
    <div v-else-if="UserFunction === 'password'" id="modify_pwd_div">
        <div  @click="toUserFunction('')"><img src="../assets/close.png" class="close" id="close_pwd_btn" ></div>
        <div id="modify_pwd_div_title">修改密码</div>
        <div id="modify_pwd_div_content">
            <div id="old_pwd_div">输入旧密码</div>
            <input type="password" placeholder="请输入旧密码" id="old_pwd_input">
            <div id="new_pwd_div">输入新密码</div>
            <input type="password" placeholder="请输入新密码" id="new_pwd_input">
            <div id="re_new_pwd_div">确认新密码</div>
            <input type="password" placeholder="请确认新密码" id="re_new_pwd_input">
        </div>
        <button id="pwd_btn">确定</button>
    </div>
    <!-- 修改密码END -->
    <!-- 添加收货地址 -->
    <div v-else-if="UserFunction === 'addSite'" class="add_address_div"  id="add_address_div">
        <div  @click="toUserFunction('')"><img src="../assets/close.png" class="close" id="close_address_btn" ></div>
        <div class="add_address_div_title">添加地址</div>
        <div class="add_address_div_content">
            <div id="aname_text">输入姓名</div>
            <input type="text" placeholder="输入姓名" v-model="newAddress.aname" id="aname_input">
            <div id="aphone_text">输入手机号</div>
            <input type="text" placeholder="请输入手机号" v-model="newAddress.aphonenNmber"  id="aphone_input">
            <div id="atext_text">收货地址</div>
            <input type="text" placeholder="请填写收货地址" v-model="newAddress.atext" id="atext_input">
        </div>
        <button id="add_address">添加</button>
    </div>
    <!-- 添加收货地址END -->
    <!-- 修改收货地址 -->
    <div v-else-if="UserFunction === 'moSite'" class="add_address_div" id="add_address_div_x">
        <div @click="toUserFunction('')"><img src="../assets/close.png" class="close" id="close_address_btn_x" ></div>
        <div class="add_address_div_title">修改地址</div>
        <div class="add_address_div_content">
            <div id="aname_text_x">输入姓名</div>
            <input type="text" placeholder="输入姓名" v-model="newAddress.aname" id="aname_input_x">
            <div id="aphone_text_x">输入手机号</div>
            <input type="text" placeholder="请输入手机号" v-model="newAddress.aphonenNmber" id="aphone_input_x">
            <div id="atext_text_x">收货地址</div>
            <input type="text" placeholder="请填写收货地址" v-model="newAddress.atext" id="atext_input_x">
        </div>
        <button id="x_address">修改</button>
    </div>

    <!-- 修改收货地址END -->
</template>

<script>
import { mapGetters } from 'vuex';
import axios from 'axios';

export default {
    data(){
        return{
            User:{},
            UserInfoWindow:"secure",
            UserFunction:'',
            address:[],
            newAPhoneNmber:"",
            newAddress:{
                'aname':'',
                'aphonenNmber':'',
                'atext':''
            },
            newUPhoneNmber:'',
        }
    },
    mounted() {
        this.getUserInfo();
        this.getUserAddress();
    },
    computed: {
        ...mapGetters(['getUserName']),
    },
    methods:{
        toUserInfoWindow(window){
            this.UserInfoWindow = window;
        },
        toUserFunction(fun){
            this.UserFunction = fun;
        },
        /*
        * 获取用户信息
        * */
       async getUserInfo(){
            const response = await axios.get('/api/getUserInfo?uname=' + this.getUserName);
            this.User = response.data;
       },
       async getUserAddress(){
            const response = await axios.get('/api/getAddressByUName?uname=' + this.getUserName);
            this.address = response.data;
       },
       async phoneChange(){
            const response = await axios.get('/api/phoneChange?uname=' + this.getUserName + '&phone=' + this.newUPhoneNmber);
            console.log(response.data);
            if(response.data === 1){
                alert("修改成功");
                this.getUserInfo();
            }else{
                alert("修改失败");
            }
       },
    }
}

</script>

<style scoped>
        body{
            background-color: #f6f6f6;
            height: 600px;
        }
        #find_phone_div{
            width: 30%;
            height: 54%;
            position: absolute;
            right: 30%;
            top: 23%;
            background-color: #f6f6f6;
        }
        #find_phone_div_title{
            margin:30px 0 20px 0;
            text-align: center;
            font-size: 20px;
        }
        #find_phone_div_content{
            margin:0 30px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
        }
        #find_phone_div>button{
            margin:0 20%;
            /*margin-top: 20px;*/
            width: 60%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
            position: absolute;
            bottom: 8%;
        }
        #find_phone_div_content>div{
            margin-bottom: 20px;
            font-size: 15px;
        }
        #find_phone_div_content>input{
            margin-top: 10px;
            width: 60%;
            height: 40px;
            border: 1px solid #76d243;
            border-radius: 5px;
        }
        #find_email_div{
            width: 30%;
            height: 54%;
            position: absolute;
            right: 30%;
            top: 23%;
            background-color: #f6f6f6;
        }
        #find_email_div_title{
            margin:30px 0 20px 0;
            text-align: center;
            font-size: 20px;
        }
        #find_email_div_content{
            margin:0 30px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
        }
        #find_email_div>button{
            margin:0 20%;
            width: 60%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
            position: absolute;
            bottom: 8%;
        }
        #find_email_div_content>div{
            margin-bottom: 20px;
            font-size: 15px;
        }
        #find_email_div_content>input{
            margin-top: 10px;
            width: 60%;
            height: 40px;
            border: 1px solid #76d243;
            border-radius: 5px;
        }
        #modify_pwd_div{
            width: 30%;
            height: 60%;
            position: absolute;
            right: 30%;
            top: 20%;
            background-color: #f6f6f6;
        }
        #modify_pwd_div_title{
            margin:30px 0 20px 0;
            text-align: center;
            font-size: 20px;
        }
        #modify_pwd_div_content{
            margin:0 30px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;

        }
        #modify_pwd_div>button{
            margin:0 20%;
            width: 60%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
            position: absolute;
            bottom: 8%;
        }
        #modify_pwd_div_content>div{
            margin-bottom: 10px;
            font-size: 15px;
        }
        #modify_pwd_div_content>input{
            margin-bottom: 10px;
            width: 60%;
            height: 40px;
            border: 1px solid #76d243;
            border-radius: 5px;
        }
        .add_address_div{
            width: 30%;
            height: 68%;
            position: absolute;
            right: 30%;
            top: 18%;
            background-color: #f6f6f6;
        }
        .add_address_div_title{
            margin:30px 0 20px 0;
            text-align: center;
            font-size: 20px;
        }
        .add_address_div_content{
            margin:0 30px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
        }
        .add_address_div>button{
            margin:0 20%;
            /*margin-top: 20px;*/
            width: 60%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
            position: absolute;
            bottom: 8%;
        }
        .add_address_div_content>div{
            margin-bottom: 20px;
            font-size: 15px;
        }
        .add_address_div_content>input{
            margin-top: 10px;
            width: 60%;
            height: 40px;
            border: 1px solid #76d243;
            border-radius: 5px;
        }
        .add_address_div_content>#atext_input{
        }
        .close{
            position: absolute;
            top: 10px;
            right: 10px;
            width:15px;
        }
        #header_div{
            background: #c9e4db;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 50px;
            margin-bottom: 10px;
        }
        #logo_div{
            display: flex;
            justify-content: left;
            align-items: center;
        }
        #logo_img{
            height: 30px;
        }
        .hander_item{
            margin-right: 15px;
            text-align: center;
        }
        a{
            text-decoration: none;
        }
        #body_div{
            height: 100%;
            margin: 0 8%;
            display: flex;
            flex-direction: row;
        }
        #body_div_left{
            height: 100%;
            width: 200px;
        }
        #body_div_left_top{
            width: 200px;
            height: 180px;
            background-color: #f1f1f1;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 25px;
        }
        #body_div_left_top>img{
            width: 80px;
            height: 80px;
            border-radius: 50%;
            margin-top: 15%;
        }
        #body_div_left_top>text{
            font-size: 25px;
        }
        #body_div_left_bottom{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        #body_div_left_bottom>div{
            padding-top: 40px;
            width: 100%;
            height: 70px;
            text-align: center;
            font-size: 15px;
            font-weight: 600;
            background-color:#ffffff;
        }
        #body_div_left_bottom>div:hover{
            background-color: #c9e4db;
        }
        ul{
            list-style: none;
            margin: 20px;
        }
        #modify_pwd,#find_phone,#find_email{
            height: 50px;
            margin-bottom: 30px;
            font-size: 18px;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-direction: row;
        }
        #modify_pwd:hover,#find_phone:hover,#find_email:hover{
            background-color: #f6f6f6;
        }
        #body_div_right{
            margin-left: 20px;
            height: 535px;
            width:100%;
            background-color: #ffffff;
        }
        .li_div{
            text-align: center;
            align-items: center;
            display: flex;
            justify-content: center;
        }
        .li_img{
            width: 30px;
        }
        #show_2_div{
            margin:0 8%;
            font-size: 18px;
            cursor: pointer;
            display: flex;
            flex-direction: column;
        }
        .show_2_div_body>text{
            margin-right: 50px;
        }
        .show_2_div_body{
            margin-top: 20px;
            margin-bottom: 20px;
            display: flex;
            justify-content: left;
            align-items: center;
            flex-direction: row;
        }
        #modify_info_btn{
            margin:0 26%;
            width: 20%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
            position: absolute;
            bottom: 13%;
            left: 9%;
        }
        #nickname_input,#age_input{
            height: 40px;
        }
        #shou_3_overflow_scroll{
            width: 100%;
            overflow-y: scroll;
            scrollbar-width: none;
            height: 300px;
        }
        #show_3_div {
            width: 95%;
            list-style: none;
            padding: 0;
        }

        #show_3_div li {
            background-color: #f0f0f0;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            position: relative;
            transition: background-color 0.3s ease;
        }

        #show_3_div li:hover {
            background-color: #ccc;
            cursor: pointer;
        }

        #show_3_div li button {
            background-color: #007bff;
            color: #fff;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            margin-right: 5px;
            width: 20%; /* 让按钮占据 li 的 50% 宽度（减去 margin-right） */
        }

        #show_3_div li button:hover {
            background-color: #0056b3;
            cursor: pointer;
        }
        
        .show_3_div_body{
            border: 1px solid #76d243;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 10px;
        }
        .aname{
            margin-right: 20px;
            margin-left: 10px;
            font-size: 18px;
        }
        .aphone{
            margin-right: 20px;
            font-size: 15px;
        }
        .aaddress{
            margin-left: 10px;
            margin-right: 20px;
            font-size: 17px;
            overflow: hidden;
        }
        .add_address_btn_class{
            margin-left: 3%;
            margin-top: 20px;
            width: 40%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
        }
    </style>