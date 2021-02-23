package com.zdy.mybatisplus.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 翟德有
 * @since 2021-02-23
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键ID
     */
        private Long id;

      /**
     * 姓名
     */
      private String name;

      /**
     * 年龄
     */
      private Integer age;

      /**
     * 邮箱
     */
      private String email;


}
