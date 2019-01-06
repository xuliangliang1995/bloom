package com.bloom.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AliyunOssReferenceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AliyunOssReferenceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andReferrerIdIsNull() {
            addCriterion("referrer_id is null");
            return (Criteria) this;
        }

        public Criteria andReferrerIdIsNotNull() {
            addCriterion("referrer_id is not null");
            return (Criteria) this;
        }

        public Criteria andReferrerIdEqualTo(Integer value) {
            addCriterion("referrer_id =", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdNotEqualTo(Integer value) {
            addCriterion("referrer_id <>", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdGreaterThan(Integer value) {
            addCriterion("referrer_id >", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("referrer_id >=", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdLessThan(Integer value) {
            addCriterion("referrer_id <", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdLessThanOrEqualTo(Integer value) {
            addCriterion("referrer_id <=", value, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdIn(List<Integer> values) {
            addCriterion("referrer_id in", values, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdNotIn(List<Integer> values) {
            addCriterion("referrer_id not in", values, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdBetween(Integer value1, Integer value2) {
            addCriterion("referrer_id between", value1, value2, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("referrer_id not between", value1, value2, "referrerId");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeIsNull() {
            addCriterion("referrer_type is null");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeIsNotNull() {
            addCriterion("referrer_type is not null");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeEqualTo(Integer value) {
            addCriterion("referrer_type =", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeNotEqualTo(Integer value) {
            addCriterion("referrer_type <>", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeGreaterThan(Integer value) {
            addCriterion("referrer_type >", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("referrer_type >=", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeLessThan(Integer value) {
            addCriterion("referrer_type <", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeLessThanOrEqualTo(Integer value) {
            addCriterion("referrer_type <=", value, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeIn(List<Integer> values) {
            addCriterion("referrer_type in", values, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeNotIn(List<Integer> values) {
            addCriterion("referrer_type not in", values, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeBetween(Integer value1, Integer value2) {
            addCriterion("referrer_type between", value1, value2, "referrerType");
            return (Criteria) this;
        }

        public Criteria andReferrerTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("referrer_type not between", value1, value2, "referrerType");
            return (Criteria) this;
        }

        public Criteria andOssBucketIsNull() {
            addCriterion("oss_bucket is null");
            return (Criteria) this;
        }

        public Criteria andOssBucketIsNotNull() {
            addCriterion("oss_bucket is not null");
            return (Criteria) this;
        }

        public Criteria andOssBucketEqualTo(String value) {
            addCriterion("oss_bucket =", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketNotEqualTo(String value) {
            addCriterion("oss_bucket <>", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketGreaterThan(String value) {
            addCriterion("oss_bucket >", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketGreaterThanOrEqualTo(String value) {
            addCriterion("oss_bucket >=", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketLessThan(String value) {
            addCriterion("oss_bucket <", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketLessThanOrEqualTo(String value) {
            addCriterion("oss_bucket <=", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketLike(String value) {
            addCriterion("oss_bucket like", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketNotLike(String value) {
            addCriterion("oss_bucket not like", value, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketIn(List<String> values) {
            addCriterion("oss_bucket in", values, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketNotIn(List<String> values) {
            addCriterion("oss_bucket not in", values, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketBetween(String value1, String value2) {
            addCriterion("oss_bucket between", value1, value2, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssBucketNotBetween(String value1, String value2) {
            addCriterion("oss_bucket not between", value1, value2, "ossBucket");
            return (Criteria) this;
        }

        public Criteria andOssKeyIsNull() {
            addCriterion("oss_key is null");
            return (Criteria) this;
        }

        public Criteria andOssKeyIsNotNull() {
            addCriterion("oss_key is not null");
            return (Criteria) this;
        }

        public Criteria andOssKeyEqualTo(String value) {
            addCriterion("oss_key =", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotEqualTo(String value) {
            addCriterion("oss_key <>", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyGreaterThan(String value) {
            addCriterion("oss_key >", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyGreaterThanOrEqualTo(String value) {
            addCriterion("oss_key >=", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLessThan(String value) {
            addCriterion("oss_key <", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLessThanOrEqualTo(String value) {
            addCriterion("oss_key <=", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyLike(String value) {
            addCriterion("oss_key like", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotLike(String value) {
            addCriterion("oss_key not like", value, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyIn(List<String> values) {
            addCriterion("oss_key in", values, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotIn(List<String> values) {
            addCriterion("oss_key not in", values, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyBetween(String value1, String value2) {
            addCriterion("oss_key between", value1, value2, "ossKey");
            return (Criteria) this;
        }

        public Criteria andOssKeyNotBetween(String value1, String value2) {
            addCriterion("oss_key not between", value1, value2, "ossKey");
            return (Criteria) this;
        }

        public Criteria andCtIsNull() {
            addCriterion("ct is null");
            return (Criteria) this;
        }

        public Criteria andCtIsNotNull() {
            addCriterion("ct is not null");
            return (Criteria) this;
        }

        public Criteria andCtEqualTo(Date value) {
            addCriterion("ct =", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotEqualTo(Date value) {
            addCriterion("ct <>", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThan(Date value) {
            addCriterion("ct >", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThanOrEqualTo(Date value) {
            addCriterion("ct >=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThan(Date value) {
            addCriterion("ct <", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThanOrEqualTo(Date value) {
            addCriterion("ct <=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtIn(List<Date> values) {
            addCriterion("ct in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotIn(List<Date> values) {
            addCriterion("ct not in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtBetween(Date value1, Date value2) {
            addCriterion("ct between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotBetween(Date value1, Date value2) {
            addCriterion("ct not between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andUtIsNull() {
            addCriterion("ut is null");
            return (Criteria) this;
        }

        public Criteria andUtIsNotNull() {
            addCriterion("ut is not null");
            return (Criteria) this;
        }

        public Criteria andUtEqualTo(Date value) {
            addCriterion("ut =", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotEqualTo(Date value) {
            addCriterion("ut <>", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtGreaterThan(Date value) {
            addCriterion("ut >", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtGreaterThanOrEqualTo(Date value) {
            addCriterion("ut >=", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtLessThan(Date value) {
            addCriterion("ut <", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtLessThanOrEqualTo(Date value) {
            addCriterion("ut <=", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtIn(List<Date> values) {
            addCriterion("ut in", values, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotIn(List<Date> values) {
            addCriterion("ut not in", values, "ut");
            return (Criteria) this;
        }

        public Criteria andUtBetween(Date value1, Date value2) {
            addCriterion("ut between", value1, value2, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotBetween(Date value1, Date value2) {
            addCriterion("ut not between", value1, value2, "ut");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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