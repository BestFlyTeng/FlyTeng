<template>
  <ElFooter class="ElFooter_2veuxkzsor"> <p>footer</p></ElFooter>
</template>
<script lang="ts">
  // @ts-nocheck
  import { defineComponent, reactive } from 'vue';
  import { ElFooter } from 'element-plus';
  import { useProvider } from '@vtj/renderer';
  export default defineComponent({
    name: 'FooterIndex',
    components: { ElFooter },
    setup(props) {
      const provider = useProvider({
        id: '2ks14fk04a',
        version: '1738897021151'
      });
      const state = reactive<Record<string, any>>({});
      return { state, props, provider };
    }
  });
</script>
<style lang="scss" scoped>
  .ElFooter_2veuxkzsor {
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
