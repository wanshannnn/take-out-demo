<!-- 空组件 -->
<template>
  <div class="empty-box">
    <el-empty v-if="!props.isSearch" description="404" />
    <el-empty v-else description="No Data" />
  </div>
</template>

<script setup lang='ts'>
const props = defineProps({
  isSearch: {
    type: Boolean,
    default: false,
  },
})
</script>

<style scoped lang="less">
.empty-box {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  margin: 20px 0;
}
</style>
