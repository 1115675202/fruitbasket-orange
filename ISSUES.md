# 项目遇到的难题以及解决方案

#### JPA LAZY 级联查询 报错 no session
>默认情况下，服务与数据库的 session 连接会在执行完 JPA 方法后断掉。使用 LAZY 级联查询方式时，是在调用实体 get级连对象() 方法时才会再
>次使用 session 向数据库发送 SQL 语句查询关联信息，而此时 session 已经断掉了，这就导致报错 no session。<br/>
>**解决办法**：在配置文件中配置 spring.jpa.open-in-view: true，此属性将注册一个 OpenEntityManagerInViewInterceptor, 它将
>EntityManager 注册到当前线程，因此在请求完成之前,您将拥有相同的EntityManager。
>
>但在使用缓存时还会导致这个问题，比如在方法上加了 @Cacheable。如果第一次调用方法时，没有调用 get级连对象() 方法，可能是因为不需要。而
>第二次调用的时候调用了 get级连对象()，因为是从缓存中拿的数据，而缓存中没有级联对象数据，所以此时会使用 session 向数据库发起 SQL 查询
>数据，而因为整个过程并未有 session 连接，所以导致了同样的 no session 错误。<br/>
>**解决办法**：避免在这种情况下使用缓存，除非能保证级联方法在每次都会调用或者不调用。
***

#### JPA 注解使用
```java
// 多对多指明关系表信息，不加会有默认的
@JoinTable(name = "role_permission_rel",
        joinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})

// 一对多指明关联字段名称，不加会有默认的
@JoinColumn(name = "user_id")
```

#### JPA LAZY 级联查询 报错 no session
>lombok @EqualsAndHashCode 自动生成了 equas() hashCode() ，如果两个对象各有属性包含了彼此，则 equals() 内会调用到对方的
>equals()，从而导致死循环，栈内存溢出。比如：jpa 在多对多查询时 双方使用 Set 容器保存对方对象，从而调用 equals() 造成问题。<br/>
>**解决办法**：
>1、@EqualsAndHashCode(exclude = {}) 但是 exclude 排除掉本对象中的对方属性，equals() 则不会调用对方的 equals()<br/>
>2、自行重写 equals() 和 hashCode()<br/>
>3、@Data 包含了 @EqualsAndHashCode，同样情况下也会出现问题。可以使用 @EqualsAndHashCode 覆盖，或者 不使用 @Data 用独立的注解
***


