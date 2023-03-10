* 任何元素    *
  tag 具有给定标记名称的元素 div
  *|E 任何命名空间（包括非命名空间）中 E 类型的元素    *|name查找和元素<fb:name><name>
  ns|E 命名空间 ns 中 E 类型的元素 fb|name查找元素<fb:name>
  #id 属性 ID 为“id”的元素 div#wrap,#logo
  .class 类名为“类”的元素 div.left,.result
  [attr]    具有名为“attr”的属性的元素（具有任何值） a[href],[title]
  [^attrPrefix]    属性名称以“attrPrefix”开头的元素。用于查找具有 HTML5 数据集的元素    [^data-],div[^data-]
  [attr=val]    属性名为“attr”且值等于“val”的元素 img[width=500],a[rel=nofollow]
  [attr="val"]    属性名为“attr”且值等于“val”的元素 span[hello="Cleveland"][goodbye="Columbus"]
  ,a[rel="nofollow"]
  [attr^=valPrefix]    属性名为“attr”且值以“valPrefix”开头的元素 a[href^=http:]
  [attr$=valSuffix]    属性名为“attr”且值以“valSuffix”结尾的元素 img[src$=.png]
  [attr*=valContaining]    元素，属性名为“attr”，值包含“valContaining” a[href*=/search/]
  [attr~=regex]    元素，其属性名为“attr”，值与正则表达式匹配 img[src~=(?i)\\.(png|jpe?g)]
  以上内容可以按任何顺序组合 div.header[title]