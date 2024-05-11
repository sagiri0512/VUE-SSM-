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
                    <li id="find_phone">
                        <div class="li_div"><img src="../assets/phone_ico.png" alt="" class="li_img">安全手机</div>
                        <div class="li_div">
                            <p v-if="UPhoneNmber === null">暂未绑定</p>
                            <p v-else>{{ UPhoneNmber }}</p>
                            <img src="../assets/Arrowright.png" alt="" class="li_img">
                        </div>
                    </li>
                    <li id="find_email">
                        <div class="li_div"><img src="../assets/Mailbox_icon.png" alt="" class="li_img">安全邮箱</div>
                        <div class="li_div">
                            <p v-if="UMailBox === null">暂未绑定</p>
                            <p v-else>{{ UMailBox }}</p>
                            <img src="../assets/Arrowright.png" alt="" class="li_img">
                        </div>
                    </li>
                    <li id="modify_pwd">
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
                        <input type="file" name="file" id="file" v-if="UNickName === null">
                    </div>
                    <div class="show_2_div_body">
                        <text>UID</text>
                        <text>{{ UID }}</text>
                    </div>
                    <div class="show_2_div_body">
                        <text>昵称</text>
                        <text id="Nickname" v-if="UNickName === null">暂未设置</text>
                        <text id="Nickname" v-else>{{UNickName}}</text>
                        <input type="text" id="nickname_input" v-if="UNickName === null">
                    </div>
                    <div class="show_2_div_body">
                        <text>性别</text>
                        <text id="sex" v-if="USex == null">暂未设置</text>
                        <text id="sex" v-else-if="USex == 1">男</text>
                        <text id="sex" v-else-if="USex == 0">女</text>
                        <div v-if="UNickName === null">
                            <label for="sex_1"  id="label_1"> <input type="radio" id="sex_1" name="sex" value="1" checked >男</label>
                            <label for="sex_0"  id="label_0"> <input type="radio" id="sex_0" name="sex" value="0" >女</label>
                        </div>
                        
                    </div>
                    <div class="show_2_div_body">
                        <text>年龄</text>
                        <text id="age" v-if="Uage === null"> 暂未设置 </text>
                        <text id="age" v-else>{{Uage}}</text>
                        <input type="text" id="age_input" v-if="Uage === null" >
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
                    <ul id="show_3_div">
                    </ul>
                </div>

                <button id="add_address_btn" class="add_address_btn_class">添加地址</button>
            </div>
            <!-- 收货地址END -->
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    data(){
        return{
            UNickName:null,
            UHeadImage:null,
            UPhoneNmber:null,
            UMailBox:null,
            UserInfoWindow:"secure",
            UID:"1",
            USex:null,
            Uage:null,
        }
    },
    computed: {
        ...mapGetters(['getUserName']),
    },
    methods:{
        toUserInfoWindow(window){
            this.UserInfoWindow = window;
        },
    }
}

</script>

<style>
        body{
            background-color: #f6f6f6;
            height: 600px;
        }
        #find_phone_div{
            display: none;
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
            display: none;
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
            display: none;
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
            display: none;
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
            width: 60%;
            overflow-y: scroll;
            scrollbar-width: none;
            height: 300px;
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
            margin-left: 130px;
            margin-top: 20px;
            width: 40%;
            height: 40px;
            background-color: #76d243;
            border: none;
            color: white;
            border-radius: 5px;
        }
    </style>