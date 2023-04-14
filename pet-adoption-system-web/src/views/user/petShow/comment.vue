<template>
  <div class="container">

    <div class="comment-send">

      <form id="commentForm" method="GET" action="http://127.0.0.1:8888/comment">
         <span class="comment-avatar">
                     <img src="avatar1.jpg" alt="avatar">
                 </span>
        <textarea class="comment-send-input" name="comment" form="commentForm" cols="80" rows="5"
                  placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"></textarea>
        <input class="comment-send-button" type="submit" value="发表评论">

      </form>

    </div>

    <div class="comment-list" id="commentList">

      <div class="comment">
        <span class="comment-avatar">
                     <img src="avatar1.jpg" alt="avatar">
                 </span>

        <div class="comment-content">
          <p class="comment-content-name">EdmundDZhang</p>
          <p class="comment-content-article">惊了</p>
          <p class="comment-content-footer">
            <span class="comment-content-footer-id">#2</span>
            <span class="comment-content-footer-device">来自安卓客户端</span>
            <span class="comment-content-footer-timestamp">2018-01-20 14:05</span>
          </p>

        </div>

        <div class="cls"></div>

      </div>

      <div class="comment comment-bottom">
         <span class="comment-avatar">
           <img src="avatar2.jpg" alt="avatar">
         </span>

        <div class="comment-content">
          <p class="comment-content-name">bilibili英雄联盟赛事</p>
          <p class="comment-content-article">Hello World!</p>
          <p class="comment-content-footer">
            <span class="comment-content-footer-id">#1</span>
            <span class="comment-content-footer-device">来自安卓客户端</span>
            <span class="comment-content-footer-timestamp">2018-01-20 13:10</span>
          </p>

        </div>

        <div class="cls"></div>

      </div>

    </div>

  </div>
</template>

<script>
/*
const express = require('express');
const app = express();
const fs = require('fs');
const sd = require('silly-datetime');
const readline = require('readline');

app.use(express.static('./public'));

let oldHtmlContent = fs.readFileSync('./index.html').toString(); //读取index.html文档

app.get('/', function (req, res) {
  res.send(oldHtmlContent);
  fs.writeFileSync('records.txt', ''); //初始化txt为空白
});

app.get('/comment', function (req, res) {

  writeRecord(req.query.comment, sd.format(new Date(), 'YYYY-MM-DD HH:mm')); //记录评论与时间

  //加载评论
  let newHtmlContent = '';
  let r = /\d{4}-\d{2}-\d{2} \d{2}:\d{2}/ //匹配日期
  let floorNumber = 2;
  let comment = '';
  //使用readline逐行读取文件
  const r1 = readline.createInterface({
    input: fs.createReadStream('./records.txt')
  })
  r1.on('line', (line) => {
    if (r.test(line)) {
      //新评论的HTML代码
      newHtmlContent =
          `<div class="comment">
              <span class="comment-avatar">
              <img src="avatar1.jpg" alt="avatar">
              </span>
              <div class="comment-content">
                  <p class="comment-content-name">EdmundDZhang</p>
                  <p class="comment-content-article">${comment}</p>
                  <p class="comment-content-footer">
                      <span class="comment-content-footer-id">#${++floorNumber}</span>
                      <span class="comment-content-footer-device">来自安卓客户端</span>
                      <span class="comment-content-footer-timestamp">${line}</span>
                  </p>
              </div>
              <div class="cls"></div>
           </div>` + newHtmlContent;
      comment = '';
    } else {
      comment += line;
    }
  }).on('close', () => {
    res.send(oldHtmlContent.replace('<div class="comment-list" id="commentList">', '<div class="comment-list" id="commentList">\n' + newHtmlContent));
  })

})


function writeRecord(comment, datetime) {

  fs.writeFileSync('./records.txt', `${comment}\n${datetime}\n`, {flag: 'a'});

}
*/


</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  font-family: Microsoft YaHei, Arial, Helvetica, sans-serif;
}

.cls {
  clear: both;
}

.container {
  width: 780px;
  min-height: 10px;
  margin: 50px auto;
  /* border: 1px solid #dfdfdf; */
}

.comment {
  min-height: 60px;
  /* border: 1px solid red; */
}

.comment-avatar img {
  margin: 20px 0 0 20px;
  width: 48px;
  height: 48px;
  border-radius: 50%;
}

.comment-content {
  float: right;
  width: 685px;
  padding-top: 15px;
  border-top: 1px solid #dfdfdf;
}

.comment-bottom > .comment-content {
  border-bottom: 1px solid #dfdfdf;
}

.comment-content-name {
  color: #6d757a;
  font-size: 12px;
  margin-bottom: 5px;
}

.comment-content-article {
  font-size: 14px;
  margin-bottom: 10px;
}

.comment-content-footer {
  color: #6d757a;
  font-size: 12px;
  margin-bottom: 15px;
}

.comment-content-footer span {
  margin-right: 10px;
}


.comment-send {
  position: relative;
  margin-bottom: 30px;
}

.comment-send-input {
  outline: none;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  transition: all 0.3s;
  position: absolute;
  top: 15px;
  left: 95px;
  resize: none;
  width: 605px;
  height: 65px;
  padding: 10px;
  border-radius: 4px;
  background: #f4f5f7;
  border: 1px solid #e5e9ef;
}

.comment-send-input:hover, .comment-send-input:active {
  background: #fff;
  color: #555;
}

.comment-send-button {
  border: none;
  cursor: pointer;
  user-select: none;
  -moz-user-select: none;
  transition: all 0.3s;
  position: absolute;
  top: 15px;
  right: 0px;
  width: 65px;
  height: 65px;
  border-radius: 4px;
  background: #1aa2d4;
  color: #fff;
  font-size: 14px;
  text-align: center;
}

.comment-send-button:hover {
  background: #1eb6e3;
}
</style>