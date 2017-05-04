package com.mk.coffee.mapper;

import com.mk.coffee.model.ProductConversionCode;
import com.mk.coffee.model.ProductConversionCodeExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductConversionCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    long countByExample(ProductConversionCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    int deleteByExample(ProductConversionCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    @Delete({
            "delete from product_conversion_code",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    @Insert({
            "insert into product_conversion_code (id, conversion_code, ",
            "order_num, order_details_id, ",
            "shopping_cart_id, product_id, ",
            "conversion_state, crate_date, ",
            "update_date, member_id)",
            "values (#{id,jdbcType=INTEGER}, #{conversionCode,jdbcType=VARCHAR}, ",
            "#{orderNum,jdbcType=VARCHAR}, #{orderDetailsId,jdbcType=VARCHAR}, ",
            "#{shoppingCartId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
            "#{conversionState,jdbcType=INTEGER}, #{crateDate,jdbcType=TIMESTAMP}, ",
            "#{updateDate,jdbcType=TIMESTAMP}, #{memberId,jdbcType=DECIMAL})"
    })
    int insert(ProductConversionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    int insertSelective(ProductConversionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    List<ProductConversionCode> selectByExample(ProductConversionCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "id, conversion_code, order_num, order_details_id, shopping_cart_id, product_id, ",
            "conversion_state, crate_date, update_date, member_id",
            "from product_conversion_code",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.ProductConversionCodeMapper.BaseResultMap")
    ProductConversionCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ProductConversionCode record, @Param("example") ProductConversionCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ProductConversionCode record, @Param("example") ProductConversionCodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ProductConversionCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    @Update({
            "update product_conversion_code",
            "set conversion_code = #{conversionCode,jdbcType=VARCHAR},",
            "order_num = #{orderNum,jdbcType=VARCHAR},",
            "order_details_id = #{orderDetailsId,jdbcType=VARCHAR},",
            "shopping_cart_id = #{shoppingCartId,jdbcType=INTEGER},",
            "product_id = #{productId,jdbcType=INTEGER},",
            "conversion_state = #{conversionState,jdbcType=INTEGER},",
            "crate_date = #{crateDate,jdbcType=TIMESTAMP},",
            "update_date = #{updateDate,jdbcType=TIMESTAMP},",
            "member_id = #{memberId,jdbcType=DECIMAL}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProductConversionCode record);

    @Update({"update product_conversion_code",
            "set conversion_state = #{conversionState}",
            "where id = #{id,jdbcType=INTEGER}"})
    int updateConversionStateById(@Param("id") int id, @Param("conversionState") int conversionState);

    @Select({"select", "id, conversion_code, order_num, order_details_id, member_id, product_id, shopping_cart_id, ",
            "conversion_state, crate_date", "from product_conversion_code",
            "where conversion_code like #{conversionCode,jdbcType=VARCHAR} and conversion_state= #{conversionState,jdbcType=INTEGER}"})
    @ResultMap({"com.mk.coffee.mapper.ProductConversionCodeMapper.BaseResultMap"})
    ProductConversionCode getProductConversionCodeByConversionCode(ProductConversionCode productConversionCode);


    ProductConversionCode getMembersByProductConversionCode(@Param("orderNum") String orderNum, @Param("productId") int productId);

    @Update({
            "update product_conversion_code " +
                    "set conversion_state=1," +
                    " update_date=now()" +
                    "where order_num like #{orderNum} and product_id=#{productId} and conversion_state=2"
    })
    int updateProductConversionState(@Param("orderNum") String orderNum, @Param("productId") int productId);

    @Update({"update product_conversion_code  set conversion_state=3," +
            "update_date=now() " +
            "where id=#{id} and conversion_state=2"})
    int updateProductConversionStateById(@Param("id") int id);

    List<ProductConversionCode> getProductConversionCodeByMemberIdAndConversionState(@Param("memberId") long memberId,
                                                                                     @Param("conversionState") int conversionState);

    List<ProductConversionCode> getAllProductConversionCodeByMemberId(@Param("memberId") long memberId);
}