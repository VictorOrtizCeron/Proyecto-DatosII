import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
import 'bootstrap/dist/css/bootstrap.css';


const app = createApp(App);

// Use the router instance
app.use(router);

app.mount('#app');
