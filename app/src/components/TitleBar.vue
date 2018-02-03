<template>
  <div class="se-container">
    <el-row>
      <p class="se-indicator">{{ indicator }}</p>
    </el-row>
    <el-row class="se-toolbar" type="flex" justify="space-between">
      <div>
        <img src="../assets/add.png" @click="onCreateNewExcel"/>
      </div>
      <div>
        <img src="../assets/open.png" class="margin-right" @click="onOpenExcel"/>
        <img src='../assets/save-as.png' class="margin-right" @click="onSaveAsNewExcel"/>
        <img src="../assets/save.png" class="margin-right" @click="onSaveExcel"/>
      </div>
    </el-row>
  </div>
</template>

<script>
const $ = require('jquery');
export default {
  name: 'TitleBar',
  props: ['fileName', 'status'],
  computed: {
    indicator: function() {
      return this.fileName + "-" + this.status;
    }
  },
  methods: {
    onCreateNewExcel: function() {
      cordova.plugins.Excel.coolMethod("mesg" , function(uri){
        alert('open file : '+uri);
      }, function(error){
        alert(error);
      });
    },
    onOpenExcel: function(){
    },
    onSaveAsNewExcel: function(){

    },
    onSaveExcel: function(){

    }
  }
};
</script>

<style scoped>
.se-container {
  padding: 4px 0;
}
.se-indicator {
  margin: 0;
  text-align: center;
  font-size: 16px;
  color: #fff;
}
.se-toolbar {
  color: #fff;
}
.se-toolbar img {
  padding: 6px;
  width: 28px;
  height: 28px;
}
.se-toolbar img.margin-right {
  margin-right: 10px;
}
</style>
