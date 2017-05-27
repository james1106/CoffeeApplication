package com.mk.coffee.mapper;

import com.mk.coffee.model.Product;
import com.mk.coffee.model.ProductExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    long countByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    int deleteByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    @Delete({
            "delete from product",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    @Insert({
            "insert into product (id, name, ",
            "original_price, price, ",
            "picture_url, sales, ",
            "create_date, summary, ",
            "config_id)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{originalPrice,jdbcType=REAL}, #{price,jdbcType=REAL}, ",
            "#{pictureUrl,jdbcType=VARCHAR}, #{sales,jdbcType=INTEGER}, ",
            "#{createDate,jdbcType=TIMESTAMP}, #{summary,jdbcType=VARCHAR}, ",
            "#{configId,jdbcType=INTEGER})"
    })
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    int insertSelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    List<Product> selectByExample(ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "id, name, original_price, price, picture_url, sales, create_date, summary, config_id",
            "from product",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.ProductMapper.BaseResultMap")
    Product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated
     */
    @Update({
            "update product",
            "set name = #{name,jdbcType=VARCHAR},",
            "original_price = #{originalPrice,jdbcType=REAL},",
            "price = #{price,jdbcType=REAL},",
            "picture_url = #{pictureUrl,jdbcType=VARCHAR},",
            "sales = #{sales,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "summary = #{summary,jdbcType=VARCHAR},",
            "config_id = #{configId,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);

    @Update({
            "update product",
            "set sales = #{sales,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByProductId(Product record);


    int updateProductById(@Param("record") Product record, @Param("productId") int productId);

}