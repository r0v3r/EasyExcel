<template>
  <div class="container">
    <div class="editor-holder">
        <el-input
            placeholder="请输入内容"
            clearable>
            <template slot="prepend">日期</template>
        </el-input>
    </div>
    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <td v-for="(header, index) in adjustedContent.header" :key="index"
                        @click="handleChooseColumn(index)">
                        {{header}}
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(row, rindex) in adjustedContent.body" :key="rindex">
                    <td v-for="(content, cindex) in row" :key="cindex" 
                        @click="handleChooseBody(rindex, cindex)">
                        {{content}}
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="editor-footer">
            <button>footer btn</button>
        </div>
    </div>
  </div>
</template>

<script>
const $ = require('jquery');
const EDITOR_HEIGHT = 50;
export default {
  name: 'ExcelEditor',
  data(){
      return {
      }
  },
  computed:{
      adjustedContent: function(){
          let tmp = this.fileContent;
        if(tmp){
            // add row index
            for(let index=0; index<tmp.length; index++){
                if(index === 0){
                    tmp[0].unshift('');
                }else{
                    tmp[index].unshift(index);
                }
            }
            return {
                header: tmp[0],
                body: tmp.slice(1)
            }
        }else{
            return {
                header:[],
                body:[]
            }
        }
      }
  },
  props: ['fileContent'],
  methods:{
      handleChooseColumn: function(index){
          if(index === 0){
              return;
          }
          let cssIndex = index + 1;
          $('tr').removeClass();
          $('td').removeClass();
          $('thead td:nth-child('+cssIndex+')').addClass('chosen');
          $('tbody td:first-child').addClass('shadowed');
          $('tbody td:nth-child('+cssIndex+')').addClass(['shadowed','chosen-col']);
          $('tbody tr:first-child td:nth-child('+cssIndex+')').addClass('focus');
      },
      handleChooseBody: function(rindex, cindex){
          if(cindex === 0){
              this.handleChooseRow(rindex);
          }else{
              this.handleChooseCell(rindex, cindex);
          }
      },
      handleChooseRow: function(rindex){
          let cssIndex = rindex+1;
          $('tr').removeClass();
          $('td').removeClass();
          $('thead td:not(:first-child)').addClass('shadowed');
          $('tbody tr:nth-child('+cssIndex+') td:first-child').addClass('chosen');
          $('tbody tr:nth-child('+cssIndex+') td').addClass(['shadowed', 'chosen-row']);
          $('tbody tr:nth-child('+cssIndex+') td:nth-child(2)').addClass('focus');
      },
      handleChooseCell: function(rindex, cindex){
          let cssRIdx = rindex+1;
          let cssCIdx = cindex+1;
          $('tr').removeClass();
          $('td').removeClass();
          $('thead td:nth-child('+cssCIdx+')').addClass('shadowed');
          $('tbody tr:nth-child('+cssRIdx+') td:first-child').addClass('shadowed');
          $('tbody tr:nth-child('+cssRIdx+') td:nth-child('+cssCIdx+')').addClass(['focus', 'chosen']);
      }
  }
}
</script>

<style scoped>
.container{
}
.editor-holder{
    padding: 5px 4px;
    z-index: 2;
    background-color:#4A90E2;
}
.table-container{
    position: absolute;
    top: 50px;
    bottom: 0px;
    overflow: scroll;
    width: 100%;
}
.editor-footer{
    width: 100%;
}
table{
    border-collapse:collapse;
     box-sizing:border-box;
    -moz-box-sizing:border-box; /* Firefox */
    -webkit-box-sizing:border-box; /* Safari */
}
table td{
    padding: .25rem .5rem;
    min-width: 36px;
    font-size: 1rem;
    line-height: 1rem;
    border: 2px solid lightgray;
}
thead td, tbody td:first-child{
    text-align: center;
    background-color: #F5F5F5;
}
tbody tr:last-child{
    border: none;
}
thead td.chosen, tbody td:first-child.chosen{
    background: rgb(159, 196, 243);
    border: 3px solid rgb(159, 196, 243);
}
td.shadowed, tbody td:first-child.shadowed{
    background-color: #E8E8E8;
}
tbody td.chosen{
    border: 3px solid rgb(159, 196, 243);
}
tbody td.focus{
    background: #ffffff;
}
tbody td.chosen-col{
    border-left: 3px solid rgb(159, 196, 243);
    border-right: 3px solid rgb(159, 196, 243);
}
tbody td.chosen-row{
    border-top: 3px solid rgb(159, 196, 243);
    border-bottom: 3px solid rgb(159, 196, 243);
}
</style>

