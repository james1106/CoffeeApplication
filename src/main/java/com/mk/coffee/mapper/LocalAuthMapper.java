package com.mk.coffee.mapper;

import com.mk.coffee.model.LocalAuth;
import com.mk.coffee.model.LocalAuthExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LocalAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    long countByExample(LocalAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    int deleteByExample(LocalAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    @Delete({
            "delete from local_auth",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    @Insert({
            "insert into local_auth (id, member_id, ",
            "token, create_date, ",
            "validity_day)",
            "values (#{id,jdbcType=INTEGER}, #{memberId,jdbcType=DECIMAL}, ",
            "#{token,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
            "#{validityDay,jdbcType=INTEGER})"
    })
    int insert(LocalAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    int insertSelective(LocalAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    List<LocalAuth> selectByExample(LocalAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "id, member_id, token, create_date, validity_day",
            "from local_auth",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.LocalAuthMapper.BaseResultMap")
    LocalAuth selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LocalAuth record, @Param("example") LocalAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LocalAuth record, @Param("example") LocalAuthExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LocalAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table local_auth
     *
     * @mbg.generated
     */
    @Update({
            "update local_auth",
            "set member_id = #{memberId,jdbcType=DECIMAL},",
            "token = #{token,jdbcType=VARCHAR},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "validity_day = #{validityDay,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LocalAuth record);

    @Delete({
            "delete from local_auth",
            "where member_id = #{id,jdbcType=DECIMAL}"
    })
    int deleteTokenByMemberId(long id);

    @Select({
            "select",
            "id, member_id, token, create_date, validity_day",
            "from local_auth",
            "where token like #{token,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.LocalAuthMapper.BaseResultMap")
    LocalAuth selectLocalAuthByToken(String token);
}