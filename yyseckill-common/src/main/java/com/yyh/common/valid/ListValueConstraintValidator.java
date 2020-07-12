package com.yyh.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yeyuhua
 * @version 1.0
 * @created 2020/6/20 10:16 下午
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private Set<Integer> set = new HashSet<>();
    // 初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    // 判断是否校验成功
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}
