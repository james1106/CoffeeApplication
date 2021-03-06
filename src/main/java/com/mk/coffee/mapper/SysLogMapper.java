package com.mk.coffee.mapper;

import com.mk.coffee.model.SysLog;
import com.mk.coffee.model.SysLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    long countByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int deleteByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_log (id, method, ",
        "url, param, user_id, ",
        "ip, spend_time, create_date, ",
        "result)",
        "values (#{id,jdbcType=INTEGER}, #{method,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{param,jdbcType=VARCHAR}, #{userId,jdbcType=INTEGER}, ",
        "#{ip,jdbcType=VARCHAR}, #{spendTime,jdbcType=VARCHAR}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{result,jdbcType=LONGVARCHAR})"
    })
    int insert(SysLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int insertSelective(SysLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    List<SysLog> selectByExampleWithBLOBs(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    List<SysLog> selectByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, method, url, param, user_id, ip, spend_time, create_date, result",
        "from sys_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.SysLogMapper.ResultMapWithBLOBs")
    SysLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") SysLog record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Update({
        "update sys_log",
        "set method = #{method,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "param = #{param,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "spend_time = #{spendTime,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "result = #{result,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(SysLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Update({
        "update sys_log",
        "set method = #{method,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "param = #{param,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=INTEGER},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "spend_time = #{spendTime,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysLog record);
}