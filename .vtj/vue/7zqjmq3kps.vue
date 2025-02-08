<template>
  <HeaderIndex></HeaderIndex>
  <WaitDevelop></WaitDevelop>
</template>
<script lang="ts">
  // @ts-nocheck
  import { defineComponent, reactive } from 'vue';
  import HeaderIndex from './lvudych1c.vue';
  import WaitDevelop from './f6i12nwsdv.vue';
  import { useProvider } from '@vtj/renderer';
  export default defineComponent({
    name: 'Home',
    components: { HeaderIndex, WaitDevelop },
    setup(props) {
      const provider = useProvider({
        id: '7zqjmq3kps',
        version: '1738902427508'
      });
      const state = reactive<Record<string, any>>({});
      return { state, props, provider };
    }
  });
</script>
<style lang="scss" scoped></style>
