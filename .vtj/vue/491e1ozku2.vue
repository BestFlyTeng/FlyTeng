<template>
  <HeaderIndex></HeaderIndex>
  <div class="div_1sf5p0ayuk">
    <h3 class="h3_1vyrmq41kc">登录</h3>
    <ElForm
      labelWidth="80px"
      :model="state.formParams"
      :inlineMessage="false"
      :statusIcon="true"
      :disabled="false"
      :showMessage="true"
      :scrollToError="false"
      :hideRequiredAsterisk="false"
      :rules="{
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: validateEmail, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度应是8-16', trigger: 'blur' }
        ]
      }"
      ref="formRef">
      <ElFormItem
        label="邮箱"
        :required="true"
        :inlineMessage="false"
        prop="email">
        <ElInput
          v-model:modelValue="state.formParams.email"
          :autosize="false"
          :showPassword="false"
          :rows="1"
          name=""
          placeholder="请输入邮箱"></ElInput
      ></ElFormItem>
      <ElFormItem
        label="密码"
        :required="true"
        :inlineMessage="false"
        prop="password">
        <ElInput
          v-model:modelValue="state.formParams.password"
          :autosize="false"
          :showPassword="true"
          :rows="1"
          name=""
          placeholder="请输入密码"></ElInput
      ></ElFormItem>
      <ElFormItem label=" ">
        <ElButton type="primary" @click="click_3goim7190s"> 登录</ElButton>
        <ElButton type="default" :link="false" @click="click_3k84jwubql">
          重置</ElButton
        >
        <ElButton type="default" class="ElButton_2a57f30btj">
          <RouterLink to="/page/2rv8zxlbzw" class="RouterLink_pfgflznxm">
            去注册</RouterLink
          ></ElButton
        ></ElFormItem
      ></ElForm
    >
  </div>
  <FooterIndex></FooterIndex>
</template>
<script lang="ts">
  // @ts-nocheck
  import { defineComponent, reactive } from 'vue';
  import { ElForm, ElFormItem, ElInput, ElButton } from 'element-plus';
  import { RouterLink } from 'vue-router';
  import HeaderIndex from './lvudych1c.vue';
  import FooterIndex from './2ks14fk04a.vue';
  import { useProvider } from '@vtj/renderer';
  export default defineComponent({
    name: 'Login',
    components: {
      HeaderIndex,
      ElForm,
      ElFormItem,
      ElInput,
      ElButton,
      RouterLink,
      FooterIndex
    },
    setup(props) {
      const provider = useProvider({
        id: '491e1ozku2',
        version: '1738984306662'
      });
      const state = reactive<Record<string, any>>({
        formParams: {
          email: '',
          password: ''
        }
      });
      return { state, props, provider };
    },
    methods: {
      async login(...args: any[]) {
        return await this.provider.apis['73u29q8208']
          .apply(this, args)
          .then((res) => {
            return res;
          });
      },
      async click_3goim7190s() {
        await this.$refs.formRef.validate((vaild) => {
          if (vaild) {
            this.login(this.state.formParams).catch((error) => {
              if (error.status === 200) {
                localStorage.setItem('token', error.data.token);
                this.$router.push('/page/7zqjmq3kps');
              }
            });
          }
        });
      },
      click_3k84jwubql() {
        this.$refs.formRef.resetFields();
      },
      validateEmail(rule, value, callback) {
        if (
          !/^[A-Za-z0-9_\-]+(\.[A-Za-z0-9_\-]+)*@[A-Za-z0-9\-]+\.[A-Za-z]{2,}$/.test(
            value
          )
        ) {
          callback(new Error('邮箱不合法'));
        } else {
          callback();
        }
        callback();
      }
    }
  });
</script>
<style lang="scss" scoped>
  .div_1sf5p0ayuk {
    padding: 1rem;
    background-color: rgb(255 255 255 / 30%);
    backdrop-filter: blur(5px);
    border-radius: 1rem;
    width: 80%;
    margin: 2rem auto;
  }

  .h3_1vyrmq41kc {
    font-size: 3rem;
    font-weight: bolder;
    background-image: linear-gradient(-45deg, #a6c0fe 0%, #f68084 100%);
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-align: center;
    margin-top: 1rem;
    margin-bottom: 1rem;
  }

  .ElButton_2a57f30btj {
    margin-left: auto;
    padding-left: 0px;
    padding-bottom: 0px;
    padding-right: 0px;
    padding-top: 0px;
  }

  .RouterLink_pfgflznxm {
    text-decoration: none;
    color: #636363;
    height: 100%;
    padding: 8px 15px;
  }
</style>
