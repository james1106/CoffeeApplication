package com.mk.coffee.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductConversionCodeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public ProductConversionCodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andConversionCodeIsNull() {
            addCriterion("conversion_code is null");
            return (Criteria) this;
        }

        public Criteria andConversionCodeIsNotNull() {
            addCriterion("conversion_code is not null");
            return (Criteria) this;
        }

        public Criteria andConversionCodeEqualTo(String value) {
            addCriterion("conversion_code =", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeNotEqualTo(String value) {
            addCriterion("conversion_code <>", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeGreaterThan(String value) {
            addCriterion("conversion_code >", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("conversion_code >=", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeLessThan(String value) {
            addCriterion("conversion_code <", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeLessThanOrEqualTo(String value) {
            addCriterion("conversion_code <=", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeLike(String value) {
            addCriterion("conversion_code like", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeNotLike(String value) {
            addCriterion("conversion_code not like", value, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeIn(List<String> values) {
            addCriterion("conversion_code in", values, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeNotIn(List<String> values) {
            addCriterion("conversion_code not in", values, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeBetween(String value1, String value2) {
            addCriterion("conversion_code between", value1, value2, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andConversionCodeNotBetween(String value1, String value2) {
            addCriterion("conversion_code not between", value1, value2, "conversionCode");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(String value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(String value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(String value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(String value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLike(String value) {
            addCriterion("order_num like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotLike(String value) {
            addCriterion("order_num not like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<String> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<String> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(String value1, String value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(String value1, String value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdIsNull() {
            addCriterion("order_details_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdIsNotNull() {
            addCriterion("order_details_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdEqualTo(String value) {
            addCriterion("order_details_id =", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdNotEqualTo(String value) {
            addCriterion("order_details_id <>", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdGreaterThan(String value) {
            addCriterion("order_details_id >", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_details_id >=", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdLessThan(String value) {
            addCriterion("order_details_id <", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdLessThanOrEqualTo(String value) {
            addCriterion("order_details_id <=", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdLike(String value) {
            addCriterion("order_details_id like", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdNotLike(String value) {
            addCriterion("order_details_id not like", value, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdIn(List<String> values) {
            addCriterion("order_details_id in", values, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdNotIn(List<String> values) {
            addCriterion("order_details_id not in", values, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdBetween(String value1, String value2) {
            addCriterion("order_details_id between", value1, value2, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailsIdNotBetween(String value1, String value2) {
            addCriterion("order_details_id not between", value1, value2, "orderDetailsId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Long value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Long value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Long value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Long value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Long value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Long value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Long> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Long> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Long value1, Long value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Long value1, Long value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIsNull() {
            addCriterion("shopping_cart_id is null");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIsNotNull() {
            addCriterion("shopping_cart_id is not null");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdEqualTo(Integer value) {
            addCriterion("shopping_cart_id =", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotEqualTo(Integer value) {
            addCriterion("shopping_cart_id <>", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdGreaterThan(Integer value) {
            addCriterion("shopping_cart_id >", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shopping_cart_id >=", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdLessThan(Integer value) {
            addCriterion("shopping_cart_id <", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdLessThanOrEqualTo(Integer value) {
            addCriterion("shopping_cart_id <=", value, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdIn(List<Integer> values) {
            addCriterion("shopping_cart_id in", values, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotIn(List<Integer> values) {
            addCriterion("shopping_cart_id not in", values, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdBetween(Integer value1, Integer value2) {
            addCriterion("shopping_cart_id between", value1, value2, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andShoppingCartIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shopping_cart_id not between", value1, value2, "shoppingCartId");
            return (Criteria) this;
        }

        public Criteria andConversionStateIsNull() {
            addCriterion("conversion_state is null");
            return (Criteria) this;
        }

        public Criteria andConversionStateIsNotNull() {
            addCriterion("conversion_state is not null");
            return (Criteria) this;
        }

        public Criteria andConversionStateEqualTo(Integer value) {
            addCriterion("conversion_state =", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateNotEqualTo(Integer value) {
            addCriterion("conversion_state <>", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateGreaterThan(Integer value) {
            addCriterion("conversion_state >", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("conversion_state >=", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateLessThan(Integer value) {
            addCriterion("conversion_state <", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateLessThanOrEqualTo(Integer value) {
            addCriterion("conversion_state <=", value, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateIn(List<Integer> values) {
            addCriterion("conversion_state in", values, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateNotIn(List<Integer> values) {
            addCriterion("conversion_state not in", values, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateBetween(Integer value1, Integer value2) {
            addCriterion("conversion_state between", value1, value2, "conversionState");
            return (Criteria) this;
        }

        public Criteria andConversionStateNotBetween(Integer value1, Integer value2) {
            addCriterion("conversion_state not between", value1, value2, "conversionState");
            return (Criteria) this;
        }

        public Criteria andCrateDateIsNull() {
            addCriterion("crate_date is null");
            return (Criteria) this;
        }

        public Criteria andCrateDateIsNotNull() {
            addCriterion("crate_date is not null");
            return (Criteria) this;
        }

        public Criteria andCrateDateEqualTo(Date value) {
            addCriterion("crate_date =", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateNotEqualTo(Date value) {
            addCriterion("crate_date <>", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateGreaterThan(Date value) {
            addCriterion("crate_date >", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("crate_date >=", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateLessThan(Date value) {
            addCriterion("crate_date <", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateLessThanOrEqualTo(Date value) {
            addCriterion("crate_date <=", value, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateIn(List<Date> values) {
            addCriterion("crate_date in", values, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateNotIn(List<Date> values) {
            addCriterion("crate_date not in", values, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateBetween(Date value1, Date value2) {
            addCriterion("crate_date between", value1, value2, "crateDate");
            return (Criteria) this;
        }

        public Criteria andCrateDateNotBetween(Date value1, Date value2) {
            addCriterion("crate_date not between", value1, value2, "crateDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table product_conversion_code
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table product_conversion_code
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}