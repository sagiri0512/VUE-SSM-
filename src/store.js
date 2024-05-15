// store.js
import { createStore } from 'vuex';

const store = createStore({
  state: {
    userName: '',//用户名
    num:0,//购物车商品数量
    search:'',//搜索框内容
    pid:'',//详细页商品id
    window:'home',//进入哪个页面
    pids:[],//
  },
  
  mutations: {
    updateUserName(state, newValue) {
      state.userName = newValue;
    },
    updateNum(state, newValue){
      state.num = newValue;
    },
    updateSearch(state, newValue){
      state.search = newValue;
    },
    updatePid(state, newValue){
      state.pid = newValue;
    },
    updateWindow(state, newValue){
      state.window = newValue;
    },
    setSelectedPids(state, newValue){
      state.pids = newValue;
    },
  },
  getters: {
    getNum: (state) => {
      return state.num;
    },
    getUserName: (state) => {
      return state.userName;
    },
    getSearch: (state) => {
      return state.search;
    },
    getPid: (state) => {
      return state.pid;
    },
    getWindow: (state) => {
      return state.window;
    },
    getSelectedPids: (state) => {
      return state.pids;
    }
  }
});

export default store;
