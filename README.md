## 目标功能
- [x] `(1)` 计算选中的行数
- [x] `(2)` 临时渲染代码颜色，当前文本相同单词的颜色一致
      本功能不跨行提示，只标记当前行内，单词相同的部分（便于实现，主要用于检测自己是否set对了对象）
- [ ] `(3)` 输入字母，选中后提示常用转义字符（XML）
- [ ] `(4)` MD表格辅助代码生成
- [ ] `(5)` 块级代码排序，比如所有的public放到前面，所有的private放到末尾，并按照拼音首字母排序

## 构建提示
下载代码创建工程，注意包含JDK和IntelliJ SDK（环境搭建自行搜索）
Build菜单下有个Prepare Plugin Mode 'XXX' For Deployment，即可生成jar包或者zip包，再通过disk安装即可

## 参考项目
> 可能在原项目上做了轻微的改动，也可能是照着原项目的思路进行重做
> 感谢原作大神们的开源精神！

- 功能1 参考：[LinesSorter](https://github.com/syllant/idea-plugin-linessorter)
- 功能2 参考：[MultiHighlight](https://github.com/huoguangjin/MultiHighlight)

