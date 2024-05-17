<template>
  <div class="body">
    <!-- 登录窗口 -->
    <div :class="loginDiv" v-if="window == 'login'">
      <h1 :style="{'text-align':'center', 'margin' : '5px'}">登录</h1>
      <div style="text-align: center;">
        <div>
          <label>用户名：</label>
          <input type="text" v-model="user.userName" 
          :style="{ borderColor: (user.userName ? 'green' : 'red') }" 
          placeholder="请输入用户名" @keyup.enter="handleLogin"><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <label >密码：</label>
          <input type="password" v-model="user.userPWD"
          :style="{ borderColor: (user.userPWD ? 'green' : 'red') , 'margin-left':'15px'}" 
          placeholder = "请输入密码" @keyup.enter="handleLogin" ><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <label>验证码：</label>
          <input type="text" v-model="user.checkCode" 
          :style="{ borderColor: (user.checkCode ? 'green' : 'red')}" 
          placeholder = "请输入验证码" @keyup.enter="handleLogin"><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <img :src="checkImg" @click="changeCheck" :style="{'width':'275.6px'}"/><br>
        </div>
        <a :style="{'font-size': '10px', 'color':'blue', 'margin-left':'184.6px'}" @click="registerWindow">还没有账号，去注册！</a>
        <div >
          <button v-if="!(user.userName == '' || user.userPWD == '' || user.checkCode == '')" type="button" @click="login" :style="{'width':'275.6px', 'height':'40px'}">登录</button>
        </div>
      </div>
    </div>
    <!-- 注册窗口 -->
    <div :class="loginDiv" v-else-if="window == 'register'">
      <h1 :style="{'text-align':'center', 'margin' : '5px'}">注册</h1>
      <div style="text-align: center;">
        <div>
          <label>用户名：</label>
          <input type="text" v-model="user.userName" 
          :style="{ borderColor: (user.userName ? 'green' : 'red') }" 
          placeholder="请输入用户名" @keyup.enter="handleRegister"><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <label >密码：</label>
          <input type="password" v-model="user.userPWD"
          :style="{ borderColor: (user.userPWD ? 'green' : 'red') , 'margin-left':'15px'}" 
          placeholder = "请输入密码" @keyup.enter="handleRegister"><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <label>验证码：</label>
          <input type="text" v-model="user.checkCode" 
          :style="{ borderColor: (user.checkCode ? 'green' : 'red')}" 
          placeholder = "请输入验证码" @keyup.enter="handleRegister"><br>
        </div>
        <div :style="{'margin-top':'5px'}">
          <img :src="checkImg" @click="changeCheck" :style="{'width':'275.6px'}"/><br>
        </div>
        <a :style="{'font-size': '10px', 'color':'blue', 'margin-left':'245.6px'}" @click="loginWindow">去登录！</a>
        <div>
          <button v-if="!(user.userName == '' || user.userPWD == '' || user.checkCode == '')" type="button" @click="register" :style="{'width':'275.6px', 'height':'40px'}">注册</button>
        </div>
      </div>
    </div>
      <!-- 注册窗口END -->
  </div>
</template>

<script>
import axios from 'axios';
import { mapState, mapGetters } from 'vuex';


export default {
  data() {
    return {
      user:{
        "userName":"",
        "userPWD":"",
        "checkCode":""
      },
      checkImg:"",
      loginDiv:"loginDiv",
      window:"login",
      componentKey:0
    };
  },
  computed: {
    ...mapState(['userName']),
    ...mapGetters(['getUserName'])
  },
  mounted() {
    this.changeCheck();
  },
  methods: {
    handleLogin(){
      if(!(this.user.userName == '' || this.user.userPWD == '' || this.user.checkCode == '')){
        this.login();
      }
    },
    handleRegister(){
      if(!(this.user.userName == '' || this.user.userPWD == '' || this.user.checkCode == '')){
        this.register();
      }
    },
    async login(){
      const userInfo = {
        userName : this.user.userName,
        userPWD : this.user.userPWD,
        checkCode : this.user.checkCode
      }
      try {
        const response = await axios.post('/api/login', userInfo);
        
        // 在这里处理后端返回的数据
        if (response.data == '1') {
          this.$store.commit('updateUserName', this.user.userName);
        } else if(response.data == '0'){
          alert("密码错误")
          // 处理登录失败的逻辑
        }else if(response.data == '2'){
          alert("验证码错误！")
          this.changeCheck();
          this.user.checkCode = ''
        }
      } catch (error) {
        console.log(error);
        alert("用户名或密码错误！")
      }
    },
    changeCheck(){
      this.checkImg = `api/kapcha?time=${new Date().getTime()}`;
    },
    registerWindow(){
      this.window = 'register'
    },
    loginWindow(){
      this.window = 'login'
    },
    async register(){
      const userInfo = {
        userName : this.user.userName,
        userPWD : this.user.userPWD,
        checkCode : this.user.checkCode
      }
      const response = await axios.post('/api/register', userInfo);

      if (response.data == '1') {
        alert("注册成功！");
        this.window = 'login';
        this.user.checkCode = '';
      } else if(response.data == '0'){
        alert('用户已存在！');
          
      }else if(response.data == '2'){
        alert("验证码错误！")
        this.changeCheck();
        this.user.checkCode = ''
      }
    }
  },
};
</script>


<style scoped>
  .loginDiv{
    background: white;
    width: 30%;
    height: 320px;
    background-color: rgba(255, 255, 255, 0.5); /* 白色背景，透明度为 50% */
    border-radius: 10px; /* 设置圆角为 10px */
    padding: 20px; /* 添加内边距 */
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  }
  .body {
    width: 100%;
    height: 100%;
    background-image: url("../assets/back.jpg");
    background-size: cover;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  input {
    width: 200px;
    height: 20px;
    padding: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-top: 5px;
  }
  button {
    width: 200px;
    height: 30px;
    margin-top: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
</style>