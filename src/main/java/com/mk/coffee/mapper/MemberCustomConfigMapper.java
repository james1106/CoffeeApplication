package com.mk.coffee.mapper;

import com.mk.coffee.model.MemberCustomConfig;
import com.mk.coffee.model.MemberCustomConfigExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberCustomConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    long countByExample(MemberCustomConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    int deleteByExample(MemberCustomConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    @Delete({
            "delete from member_custom_config",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    @Insert({
            "insert into member_custom_config (id, member_id, ",
            "custom_config_id, create_date)",
            "values (#{id,jdbcType=INTEGER}, #{memberId,jdbcType=DECIMAL}, ",
            "#{customConfigId,jdbcType=INTEGER}, #{createDate,jdbcType=TIMESTAMP})"
    })
    int insert(MemberCustomConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    int insertSelective(MemberCustomConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    List<MemberCustomConfig> selectByExample(MemberCustomConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    @Select({
            "select",
            "id, member_id, custom_config_id, create_date",
            "from member_custom_config",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.mk.coffee.mapper.MemberCustomConfigMapper.BaseResultMap")
    MemberCustomConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MemberCustomConfig record, @Param("example") MemberCustomConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MemberCustomConfig record, @Param("example") MemberCustomConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MemberCustomConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_custom_config
     *
     * @mbg.generated
     */
    @Update({
            "update member_custom_config",
            "set member_id = #{memberId,jdbcType=DECIMAL},",
            "custom_config_id = #{customConfigId,jdbcType=INTEGER},",
            "create_date = #{createDate,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MemberCustomConfig record);

    List<MemberCustomConfig> getMyMemberCustomConfig(@Param("memberId") long memberId);
}