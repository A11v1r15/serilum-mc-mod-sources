/*
 * This is the latest source code of Collective.
 * Minecraft version: 1.18.2.
 *
 * Please don't distribute without permission.
 * For all Minecraft modding projects, feel free to visit my profile page on CurseForge or Modrinth.
 *  CurseForge: https://curseforge.com/members/serilum/projects
 *  Modrinth: https://modrinth.com/user/serilum
 *  Overview: https://serilum.com/
 *
 * If you are feeling generous and would like to support the development of the mods, you can!
 *  https://ricksouth.com/donate contains all the information. <3
 *
 * Thanks for looking at the source code! Hope it's of some use to your project. Happy modding!
 */

package com.natamus.collective.functions;

import java.util.Map;
import java.util.function.Function;

public class HashMapFunctions {
    public static <K, V> V computeIfAbsent(Map<K, V> cache, K key, Function<? super K, ? extends V> function) {
        V result = cache.get(key);

        if (result == null) {
            result = function.apply(key);
            cache.put(key, result);
        }

        return result;
    }
}