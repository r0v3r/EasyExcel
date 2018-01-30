<template>
  <el-container id="app">
    <el-header>
      <title-bar :file-name="fileName" :status="status"></title-bar>
    </el-header>
    <div class="main">
      <excel-editor :file-content="fileContent"></excel-editor>
    </div>
  </el-container>
</template>

<script>
import TitleBar from '@/components/TitleBar'
import ExcelEditor from '@/components/ExcelEditor'
export default {
  name: 'App',
  data(){
    return {
      fileName: 'file',
      status: 'saved',
      fileContent:  [
        ['header1-this-is-quite-a-loooooooooooooooooooooong-header', 'header2', 'header3', 'header4', 'header5', 'header6'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
        ['content', 'content', 'content', 'content', 'content', 'content'],
      ]
    }
  },
  components: {
    'title-bar': TitleBar,
    'excel-editor': ExcelEditor 
  }
}
</script>

<style>
body{
  margin: 0;
  padding: 0;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.el-header{
  background-color:#4A90E2;
}
.main{
  position: fixed;
  top: 60px;
  bottom: 0;
  overflow: scroll;
  width: 100%;
  padding: 0;
}
</style>