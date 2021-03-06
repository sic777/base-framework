package com.sic777.db.redis.collection;

import com.sic777.db.redis.Redis;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import java.util.List;


/**
 * <p>对redis存储类型为list的操作</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class RedisList {
    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(String key) {
        return llen(SafeEncoder.encode(key));
    }

    /**
     * List长度
     *
     * @param key
     * @return 长度
     */
    public long llen(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.llen(key);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(byte[] key, int index, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String status = jedis.lset(key, index, value);
            return status;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 覆盖操作,将覆盖List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    public String lset(String key, int index, String value) {
        return lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
    }

    /**
     * 在value的相对位置插入记录
     *
     * @param key
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    public long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
    }

    /**
     * 在指定位置插入记录
     *
     * @param key
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    public long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.linsert(key, where, pivot, value);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public String lindex(String key, int index) {
        return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
    }

    /**
     * 获取List中指定位置的值
     *
     * @param key
     * @param index 位置
     * @return 值
     **/
    public byte[] lindex(byte[] key, int index) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            byte[] value = jedis.lindex(key, index);
            return value;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }


    /**
     * rpoplpush的阻塞版本，这个版本有第三个参数用于设置阻塞时间，
     * 即如果源LIST为空，那么可以阻塞监听timeout的时间，如果有元素了则执行操作。
     *
     * @param source
     * @param destination
     * @param timeout
     * @return
     * @deprecated
     */
    public String brpoplpush(String source, String destination, int timeout) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            return jedis.brpoplpush(source, destination, timeout);
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。
     * 将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。
     *
     * @param source
     * @param destination
     * @return 获取并推入的元素
     */
    public String rpoplpush(String source, String destination) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            return jedis.rpoplpush(source, destination);
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }


    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String lpop(String key) {
        return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
    }

    /**
     * 将List中的第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public byte[] lpop(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            byte[] value = jedis.lpop(key);
            return value;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 将List中最后第一条记录移出List
     *
     * @param key
     * @return 移出的记录
     */
    public String rpop(String key) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String value = jedis.rpop(key);
            return value;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 向List尾部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(String key, String value) {
        return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.rpush(key, value);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.rpush(key, value);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 向List中追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long lpush(byte[] key, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.lpush(key, value);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end
     * @return List
     */
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<String> list = jedis.lrange(key, start, end);
            return list;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 获取指定范围的记录，可以做为分页使用
     *
     * @param key
     * @param start
     * @param end   如果为负数，则尾部开始计算
     * @return List
     */
    public List<byte[]> lrange(byte[] key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            List<byte[]> list = jedis.lrange(key, start, end);
            return list;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(byte[] key, int c, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            long count = jedis.lrem(key, c, value);
            return count;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 删除List中c条记录，被删除的记录值为value
     *
     * @param key
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    public long lrem(String key, int c, String value) {
        return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    public String ltrim(byte[] key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = Redis.instance().getRedisPool().getResource();
            String str = jedis.ltrim(key, start, end);
            return str;
        } finally {
            Redis.instance().closeJedis(jedis);
        }
    }

    /**
     * 算是删除吧，只保留start与end之间的记录
     *
     * @param key
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    public String ltrim(String key, int start, int end) {
        return ltrim(SafeEncoder.encode(key), start, end);
    }
}
