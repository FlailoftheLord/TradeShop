/*
 *     Copyright (c) 2016-2017 SparklingComet @ http://shanerx.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * NOTICE: All modifications made by others to the source code belong
 * to the respective contributor. No contributor should be held liable for
 * any damages of any kind, whether be material or moral, which were
 * caused by their contribution(s) to the project. See the full License for more information
 */

package org.shanerx.tradeshop.object;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ShopChunk implements Serializable {

	private transient World world;
	@SerializedName("world")
	private String worldName;
	private int x, z;
	transient Chunk chunk;

	public ShopChunk(World w, int x, int z) {
		this.world = w;
		worldName = w.getName();
		this.x = x;
		this.z = z;
		chunk = world.getChunkAt(x, z);
	}

	public String serialize() {
		return new Gson().toJson(this);
	}

	public static ShopChunk deserialize(String loc) {
		ShopChunk chunk = new Gson().fromJson(loc, ShopChunk.class);
		chunk.world = Bukkit.getWorld(chunk.worldName);
		chunk.chunk = chunk.world.getChunkAt(chunk.x, chunk.z);
		return chunk;
	}

	public World getWorld() {
		return world;
	}

	public String getWorldName() {
		return worldName;
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public Chunk getChunk() {
		return chunk;
	}
}