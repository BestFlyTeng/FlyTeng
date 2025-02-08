<template>
  <ElRow class="ElRow_pfgbo5vh6">
    <ElCol :span="6" class="ElCol_sz29dyy6y">
      <component is="div" class="component_wio73s0wq">
        <img
          src="/src/assets/logo.png"
          width=""
          height=""
          class="img_4n7tsci71t" /></component
    ></ElCol>
    <ElCol :span="18" class="ElCol_3k84hglmux">
      <component is="div" class="component_3nrqf6epkp">
        <ElMenu
          mode="horizontal"
          :router="true"
          :collapse="false"
          :ellipsis="false"
          :defaultActive="state.defaultActive"
          :closeOnClickOutside="true"
          class="ElMenu_anfzx38lyp">
          <ElMenuItem index="/page/7zqjmq3kps"> 首页</ElMenuItem>
          <ElSubMenu v-show="!isLogin" index="">
            <template #title="scope_el8dhqakb2">
              <component is="div"> 我的</component>
            </template>
            <ElMenuItem index="" @click="click_esbld5wpqm">
              退出登录</ElMenuItem
            ></ElSubMenu
          >
          <ElMenuItem v-show="isLogin" index="/page/491e1ozku2">
            登录</ElMenuItem
          ></ElMenu
        ></component
      ></ElCol
    ></ElRow
  >
</template>
<script lang="ts">
  // @ts-nocheck
  import { defineComponent, reactive } from 'vue';
  import { ElRow, ElCol, ElMenu, ElMenuItem, ElSubMenu } from 'element-plus';
  import { useProvider } from '@vtj/renderer';
  export default defineComponent({
    name: 'Header',
    components: { ElRow, ElCol, ElMenu, ElMenuItem, ElSubMenu },
    setup(props) {
      const provider = useProvider({ id: 'lvudych1c', version: '1739002489634' });
      const state = reactive<Record<string, any>>({
        defaultActive: '/',
        token: localStorage.getItem('token')
      });
      return { state, props, provider };
    },
    computed: {
      isLogin() {
        return (this.state.isLogin =
          this.state.token === undefined ||
          this.state.token === null ||
          this.state.token === '');
      },
      watcher_ibxj1m0xt3() {
        this.$route.path;
      }
    },
    methods: {
      async logout(...args: any[]) {
        return await this.provider.apis['g5y4h7xj6h']
          .apply(this, args)
          .then((res) => res);
      },
      click_esbld5wpqm() {
        this.logout().catch((result) => {});
        localStorage.removeItem('token');
        this.$router.push('/page/7zqjmq3kps');
        this.state.token = localStorage.getItem('token');
      }
    },
    watch: {
      watcher_ibxj1m0xt3: {
        deep: true,
        immediate: true,
        handler() {
          this.state.defaultActive =
            this.$route.path === '/' ? '/page/7zqjmq3kps' : this.$route.path;
        }
      }
    }
  });
</script>
<style lang="scss" scoped>
  .el-menu--horizontal .el-menu-item:not(.is-disabled):focus,
  .el-menu--horizontal .el-menu-item:not(.is-disabled):hover {
    background-color: transparent;
  }

  .ElRow_pfgbo5vh6 {
    height: 5rem;
    background-color: rgb(255 255 255 / 30%);
    backdrop-filter: blur(5px);
  }

  .ElCol_sz29dyy6y {
    display: flex;
    height: 100%;
    justify-content: center;
  }

  .component_wio73s0wq {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .img_4n7tsci71t {
    height: 70%;
  }

  .ElCol_3k84hglmux {
    display: flex;
    flex-direction: row-reverse;
    height: 100%;
  }

  .component_3nrqf6epkp {
    height: 100%;
  }

  .ElMenu_anfzx38lyp {
    background-color: transparent;
    height: 100%;
    display: flex;
    justify-content: flex-end;
    border-bottom: none;
  }
</style>
