package com.mk.coffee.mapper;

import com.mk.coffee.model.HaveCoupon;
import com.mk.coffee.model.HaveCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface HaveCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    long countByExample(HaveCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    int deleteByExample(HaveCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    @Insert({
        "insert into havecoupon (id, members_id, ",
        "coupon_id, is_use, quota, ",
        "create_date)",
        "values (#{id,jdbcType=INTEGER}, #{membersId,jdbcType=DECIMAL}, ",
        "#{couponId,jdbcType=INTEGER}, #{isUse,jdbcType=BIT}, #{quota,jdbcType=TINYINT}, ",
        "#{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(HaveCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    int insertSelective(HaveCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    List<HaveCoupon> selectByExample(HaveCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HaveCoupon record, @Param("example") HaveCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table havecoupon
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HaveCoupon record, @Param("example") HaveCouponExample example);
}