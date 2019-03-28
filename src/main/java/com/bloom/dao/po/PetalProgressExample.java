package com.bloom.dao.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PetalProgressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PetalProgressExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPetalIdIsNull() {
            addCriterion("petal_id is null");
            return (Criteria) this;
        }

        public Criteria andPetalIdIsNotNull() {
            addCriterion("petal_id is not null");
            return (Criteria) this;
        }

        public Criteria andPetalIdEqualTo(Integer value) {
            addCriterion("petal_id =", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdNotEqualTo(Integer value) {
            addCriterion("petal_id <>", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdGreaterThan(Integer value) {
            addCriterion("petal_id >", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("petal_id >=", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdLessThan(Integer value) {
            addCriterion("petal_id <", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdLessThanOrEqualTo(Integer value) {
            addCriterion("petal_id <=", value, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdIn(List<Integer> values) {
            addCriterion("petal_id in", values, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdNotIn(List<Integer> values) {
            addCriterion("petal_id not in", values, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdBetween(Integer value1, Integer value2) {
            addCriterion("petal_id between", value1, value2, "petalId");
            return (Criteria) this;
        }

        public Criteria andPetalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("petal_id not between", value1, value2, "petalId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdIsNull() {
            addCriterion("gardener_id is null");
            return (Criteria) this;
        }

        public Criteria andGardenerIdIsNotNull() {
            addCriterion("gardener_id is not null");
            return (Criteria) this;
        }

        public Criteria andGardenerIdEqualTo(Integer value) {
            addCriterion("gardener_id =", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdNotEqualTo(Integer value) {
            addCriterion("gardener_id <>", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdGreaterThan(Integer value) {
            addCriterion("gardener_id >", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("gardener_id >=", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdLessThan(Integer value) {
            addCriterion("gardener_id <", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdLessThanOrEqualTo(Integer value) {
            addCriterion("gardener_id <=", value, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdIn(List<Integer> values) {
            addCriterion("gardener_id in", values, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdNotIn(List<Integer> values) {
            addCriterion("gardener_id not in", values, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdBetween(Integer value1, Integer value2) {
            addCriterion("gardener_id between", value1, value2, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andGardenerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("gardener_id not between", value1, value2, "gardenerId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdIsNull() {
            addCriterion("retention_curve_id is null");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdIsNotNull() {
            addCriterion("retention_curve_id is not null");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdEqualTo(Integer value) {
            addCriterion("retention_curve_id =", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdNotEqualTo(Integer value) {
            addCriterion("retention_curve_id <>", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdGreaterThan(Integer value) {
            addCriterion("retention_curve_id >", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("retention_curve_id >=", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdLessThan(Integer value) {
            addCriterion("retention_curve_id <", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdLessThanOrEqualTo(Integer value) {
            addCriterion("retention_curve_id <=", value, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdIn(List<Integer> values) {
            addCriterion("retention_curve_id in", values, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdNotIn(List<Integer> values) {
            addCriterion("retention_curve_id not in", values, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdBetween(Integer value1, Integer value2) {
            addCriterion("retention_curve_id between", value1, value2, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andRetentionCurveIdNotBetween(Integer value1, Integer value2) {
            addCriterion("retention_curve_id not between", value1, value2, "retentionCurveId");
            return (Criteria) this;
        }

        public Criteria andFireTimeIsNull() {
            addCriterion("fire_time is null");
            return (Criteria) this;
        }

        public Criteria andFireTimeIsNotNull() {
            addCriterion("fire_time is not null");
            return (Criteria) this;
        }

        public Criteria andFireTimeEqualTo(Date value) {
            addCriterion("fire_time =", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotEqualTo(Date value) {
            addCriterion("fire_time <>", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeGreaterThan(Date value) {
            addCriterion("fire_time >", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fire_time >=", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeLessThan(Date value) {
            addCriterion("fire_time <", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeLessThanOrEqualTo(Date value) {
            addCriterion("fire_time <=", value, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeIn(List<Date> values) {
            addCriterion("fire_time in", values, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotIn(List<Date> values) {
            addCriterion("fire_time not in", values, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeBetween(Date value1, Date value2) {
            addCriterion("fire_time between", value1, value2, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireTimeNotBetween(Date value1, Date value2) {
            addCriterion("fire_time not between", value1, value2, "fireTime");
            return (Criteria) this;
        }

        public Criteria andFireIsNull() {
            addCriterion("fire is null");
            return (Criteria) this;
        }

        public Criteria andFireIsNotNull() {
            addCriterion("fire is not null");
            return (Criteria) this;
        }

        public Criteria andFireEqualTo(Byte value) {
            addCriterion("fire =", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireNotEqualTo(Byte value) {
            addCriterion("fire <>", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireGreaterThan(Byte value) {
            addCriterion("fire >", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireGreaterThanOrEqualTo(Byte value) {
            addCriterion("fire >=", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireLessThan(Byte value) {
            addCriterion("fire <", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireLessThanOrEqualTo(Byte value) {
            addCriterion("fire <=", value, "fire");
            return (Criteria) this;
        }

        public Criteria andFireIn(List<Byte> values) {
            addCriterion("fire in", values, "fire");
            return (Criteria) this;
        }

        public Criteria andFireNotIn(List<Byte> values) {
            addCriterion("fire not in", values, "fire");
            return (Criteria) this;
        }

        public Criteria andFireBetween(Byte value1, Byte value2) {
            addCriterion("fire between", value1, value2, "fire");
            return (Criteria) this;
        }

        public Criteria andFireNotBetween(Byte value1, Byte value2) {
            addCriterion("fire not between", value1, value2, "fire");
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