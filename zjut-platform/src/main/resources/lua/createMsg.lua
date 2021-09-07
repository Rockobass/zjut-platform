redis.call("lpush", KEYS[1], ARGV[1])

redis.call("hmset", KEYS[2], "senderId", ARGV[2], "receiverId", ARGV[3], "sendTime", ARGV[4], "title", ARGV[5], "content", ARGV[6], "senderName", ARGV[7], "readTime", ARGV[8])

redis.call("lpush", KEYS[3], ARGV[1])

redis.call("lpush", KEYS[4], ARGV[1])

