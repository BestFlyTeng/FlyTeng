<template>
  <HeaderIndex></HeaderIndex>
  <div class="div_491e1qz9e4">
    <h3 class="h3_5mnx5se2kb">注册</h3>
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
        name: [
          { required: true, message: '昵称不能为空', trigger: 'blur' },
          { min: 1, max: 20, message: '长度应该是1-20位', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { validator: validateEmail, trigger: 'blur' }
        ],
        code: [
          { required: true, message: '请输入邮箱验证码', trigger: 'blur' },
          { min: 6, max: 6, message: '长度应是6', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 8, max: 16, message: '长度应是8-16', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ]
      }"
      ref="formRef">
      <ElFormItem
        label="昵称"
        :required="true"
        :inlineMessage="false"
        prop="name">
        <ElInput
          v-model:modelValue="state.formParams.name"
          :autosize="false"
          :showPassword="false"
          :rows="1"
          name=""
          placeholder="请输入昵称"></ElInput
      ></ElFormItem>
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
        label="验证码"
        :required="true"
        :inlineMessage="false"
        prop="code"
        class="ElFormItem_xsg71q3o25">
        <ElInput
          v-model:modelValue="state.formParams.code"
          :autosize="true"
          :showPassword="false"
          :rows="1"
          name=""
          placeholder="请输入验证码"
          class="ElInput_xvzszfwqrx"></ElInput>
        <ElButton
          type="primary"
          :round="true"
          :loading="state.countDisabled"
          :disabled="state.countDisabled"
          class="ElButton_pfgcvyjyi"
          @click="click_pfgcvyjyi">
          <span v-show="!state.countDisabled"> 获取验证码</span>
          <span v-show="state.countDisabled"> {{ state.count }}</span></ElButton
        ></ElFormItem
      >
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
      <ElFormItem
        label="确认密码"
        :required="true"
        :inlineMessage="false"
        prop="confirmPassword">
        <ElInput
          v-model:modelValue="state.formParams.confirmPassword"
          :autosize="false"
          :showPassword="true"
          :rows="1"
          name=""
          placeholder="请确认密码"></ElInput
      ></ElFormItem>
      <ElFormItem label=" ">
        <ElButton type="primary" @click="click_2a57bfzw36"> 注册</ElButton>
        <ElButton type="default" @click="click_231zg0dn1c"> 重置</ElButton>
        <ElButton type="default" class="ElButton_7p3pxiuik1">
          <RouterLink to="/page/491e1ozku2" class="RouterLink_86trm3wh47">
            去登录</RouterLink
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
    name: 'Registry',
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
        id: '2rv8zxlbzw',
        version: '1738984680706'
      });
      const state = reactive<Record<string, any>>({
        formParams: {
          name: '',
          email: '',
          code: '',
          password: '',
          confirmPassword: ''
        },
        count: 120,
        countDisabled: false
      });
      return { state, props, provider };
    },
    methods: {
      async registry(...args: any[]) {
        return await this.provider.apis['2obn27rf7o']
          .apply(this, args)
          .then((res) => {
            return res;
          });
      },
      async verify(...args: any[]) {
        return await this.provider.apis['h8xtpcrdew']
          .apply(this, args)
          .then((res) => {
            return res;
          });
      },
      async click_pfgcvyjyi() {
        if (this.state.count > 0) {
          this.verify({ to: this.state.formParams.email }).catch((error) => {
            if (error.status !== 200) return;
            this.state.countDisabled = true; // 禁用按钮
            const timer = setInterval(() => {
              this.state.count -= 1;
              if (this.state.count === 0) {
                clearInterval(timer); // 清除定时器
                this.state.countDisabled = false; // 恢复按钮
                this.state.count = 120; // 重置倒计时
              }
            }, 1000); // 每秒更新
          });
        }
      },
      async click_2a57bfzw36() {
        await this.$refs.formRef.validate((valid) => {
          if (valid) {
            this.registry(this.state.formParams).catch((result) => {
              if (result.status === 200) this.$router.push('/page/491e1ozku2');
            });
          }
        });
      },
      click_231zg0dn1c() {
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
      },
      validatePass(rule, value, callback) {
        if (value !== this.state.formParams.password) {
          callback(new Error('两次密码不一致'));
        } else {
          callback();
        }
        callback();
      }
    }
  });
</script>
<style lang="scss" scoped>
  .div_491e1qz9e4 {
    padding: 1rem;
    background-color: rgb(255 255 255 / 30%);
    backdrop-filter: blur(5px);
    border-radius: 1rem;
    width: 80%;
    margin: 2rem auto;
  }

  .h3_5mnx5se2kb {
    font-size: 3rem;
    font-weight: bolder;
    background-image: linear-gradient(-45deg, #a6c0fe 0%, #f68084 100%);
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-align: center;
    margin-top: 1rem;
    margin-bottom: 1rem;
  }

  .ElFormItem_xsg71q3o25 {
    display: flex;
  }

  .ElInput_xvzszfwqrx {
    flex: 10;
  }

  .ElButton_pfgcvyjyi {
    flex: 1;
    margin: 0 0 0 1rem;
  }

  .ElButton_7p3pxiuik1 {
    margin-left: auto;
    padding: 0;
  }

  .RouterLink_86trm3wh47 {
    text-decoration: none;
    color: #636363;
    padding: 8px 15px;
  }
</style>
