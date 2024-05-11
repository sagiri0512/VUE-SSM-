// main.js
import { createApp } from 'vue';
import App from './App.vue';

import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'

import store from './store'; // 导入 Vuex 的 store 实例

const app = createApp(App);

app.use(ElementPlus);

app.use(store); // 注册 Vuex 的 store 实例

app.mount('#app');
