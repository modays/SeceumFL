
# 分类-混淆矩阵
confusion_matrix = {
    # 列名, 数组内名称不用翻译，原样显示
    "header": ["1类", "2类", "3类"],
    # 表格数据，按行
    "data": [
        ["1类", 195, 12, 9],
        ["2类", 17, 236, 31],
        ["3类", 8, 16, 185]
    ]
}

# 聚类-列联矩阵
contingency_matrix = {
    # 列名, 数组内名称不用翻译，原样显示
    "header": ["cluster0", "cluster1", "cluster2"],
    "data": [
        # "label0" "label1" 字样不用翻译，原样显示
        ["label0", "38 (6.7019%)", "29 (5.1146%)", "145 (25.5732%)"],
        ["label1", "80 (14.1093%)", "275 (48.5009%)", "0 (0.0000%)"]
    ]

}

