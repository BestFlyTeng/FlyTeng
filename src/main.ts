import {
  createProvider,
  LocalService,
  createModules,
  NodeEnv,
  autoUpdate,
  notify,
  loading,
  createAdapter,
  createServiceRequest
} from '@vtj/web';
import { createApp } from 'vue';
import router from './router';
import App from './App.vue';
import { name, description } from '../package.json';
import './style/index.scss';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';

const app = createApp(App);
const adapter = createAdapter({ loading, notify });
const service = new LocalService(createServiceRequest(notify));
const { provider, onReady } = createProvider({
  nodeEnv: process.env.NODE_ENV as NodeEnv,
  modules: createModules(),
  service,
  adapter,
  router,
  dependencies: {
    Vue: () => import('vue'),
    VueRouter: () => import('vue-router')
  },
  project: {
    id: name,
    name: description
  }
});

onReady(async () => {
  app.use(router);
  app.use(provider);
  app.use(ElementPlus);
  app.mount('#app');
});

if (process.env.NODE_ENV === 'production') {
  autoUpdate();
}
