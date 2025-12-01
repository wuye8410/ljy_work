// 地址验证（非空且至少5个字符，若可选则去掉!value的判断）
export const validateAddress = (rule, value, callback) => {
    // 若地址为必填项：先判断非空，再校验长度
    if (!value) {
        return callback(new Error("地址不能为空"));
    }
    // 去除首尾空格后校验（避免纯空格被误认为有效）
    if (value.trim().length >= 5) {
        callback();
    } else {
        callback(new Error("地址至少5个字符（不含首尾空格）"));
    }
};