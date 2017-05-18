package com.mk.coffee.mapper;

import com.mk.coffee.model.SysUser;
import com.mk.coffee.model.SysUserExample;
import com.mk.coffee.model.SysUserKey;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    long countByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    int deleteByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    @Delete({
            "delete from sys_user",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(SysUserKey key);

    @Delete({
            "delete from sys_user",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "or username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByOrPrimaryKey(SysUserKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    @Insert({
            "insert into sys_user (user_id, username, ",
            "mobile, email, password, ",
            "status, create_id, ",
            "create_date, update_id, ",
            "update_date, salt, ",
            "head_portrait)",
            "values (#{userId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
            "#{mobile,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=INTEGER}, #{createId,jdbcType=INTEGER}, ",
            "#{createDate,jdbcType=TIMESTAMP}, #{updateId,jdbcType=INTEGER}, ",
            "#{updateDate,jdbcType=TIMESTAMP}, #{salt,jdbcType=VARCHAR}, ",
            "#{headPortrait,jdbcType=VARCHAR})"
    })
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    List<SysUser> selectByExample(SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "user_id, username, mobile, email, password, status, create_id, create_date, ",
            "update_id, update_date, salt, head_portrait",
            "from sys_user",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and username = #{username,jdbcType=VARCHAR}"
    })
    @ResultMap("com.mk.coffee.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByPrimaryKey(SysUserKey key);

    @Select({
            "select",
            "user_id, username, mobile, email, password, status, create_id, create_date, ",
            "update_id, update_date, salt, head_portrait",
            "from sys_user",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "or username = #{username,jdbcType=VARCHAR}"
    })
    @ResultMap("com.mk.coffee.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByOrPrimaryKey(SysUserKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user
     *
     * @mbg.generated
     */
    @Update({
            "update sys_user",
            "set mobile = #{mobile,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER},",
            "create_id = #{createId,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP},",
            "update_id = #{updateId,jdbcType=INTEGER},",
            "update_date = #{updateDate,jdbcType=TIMESTAMP},",
            "salt = #{salt,jdbcType=VARCHAR},",
            "head_portrait = #{headPortrait,jdbcType=VARCHAR}",
            "where user_id = #{userId,jdbcType=INTEGER}",
            "and username = #{username,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysUser record);

    @Select({
            "select",
            "user_id, username, mobile, email, password, status, create_id, create_date, ",
            "update_id, update_date, salt,head_portrait",
            "from sys_user",
            "where username like #{userName} and password like #{password}"
    })
    @ResultMap("com.mk.coffee.mapper.SysUserMapper.BaseResultMap")
    SysUser loginAdmin(@Param("userName") String userName, @Param("password") String password);


    @Select({
            "select",
            "user_id, username, mobile, email, status, create_id, create_date, ",
            "update_id, update_date, salt,head_portrait",
            "from sys_user"
    })
    List<SysUser> getSysUsers();
}