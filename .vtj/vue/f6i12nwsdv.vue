<template>
  <div class="div_fa1n0dq44o">
    <p>待开发</p>
  </div>
</template>
<script lang="ts">
  // @ts-nocheck
  import { defineComponent, reactive } from 'vue';
  import { useProvider } from '@vtj/renderer';
  export default defineComponent({
    name: 'WaitDevelop',
    setup(props) {
      const provider = useProvider({
        id: 'f6i12nwsdv',
        version: '1738903118197'
      });
      const state = reactive<Record<string, any>>({});
      return { state, props, provider };
    }
  });
</script>
<style lang="scss" scoped>
  .div_fa1n0dq44o {
    position: absolute;
    inset: 50% auto auto 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 5rem;
    font-family: STZhongsong;
    color: #f50c3b;
    font-style: italic;
    transform: translate(-50%, -50%);
  }
</style>
