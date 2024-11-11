local function expireAPI_44(keys,args)
    local key=keys[1]
    local expire = args[1]
    local count =args[2]

    if redis.call('exists',key) ==0 then
        redis.call('setex',key,expire,1)
        return true
    end

    if redis.call('get',key) >=count then
    return false
    end
    redis.call('incr',key)
    return true

end
redis.register_function('expireAPI_44', expireAPI_44)



local function buy_44(keys,args)
    local key=keys[1]
    if redis.call('hget',key,'total') =='0' then
        return -1
    end

    -- hincrby是redis的一个命令，用于对哈希表中的一个字段的值进行加减操作。
    return redis.call('hincrby',key,'total',-1)

end
redis.register_function('buy_44', buy_44)