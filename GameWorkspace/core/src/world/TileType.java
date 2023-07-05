package world;

import java.util.HashMap;

public enum TileType {
	
	
	GRASS1(1,false),
	GRASS2(2,false),
	SAND3(3,false),
	SAND_GRASS1(4,false),
	SAND_GRASS2(5,false),
	SAND_GRASS3(6,false),
	SAND_GRASS4(7,false),
	SAND_GRASS5(8,false),
	SAND_GRASS6(9,false),
	SAND_GRASS7(10,false),
	SAND_GRASS8(11,false),
	SAND_GRASS9(12,false),
	SAND_GRASS10(13,false),
	SAND_GRASS11(14,false),
	SAND_GRASS12(15,false),
	TREE1(16,true),
	DIRT1(17,false),
	WATER1(18,true),
	WATER2(19,true),
	WATER_GRASS1(20,true),
	WATER_GRASS2(21,true),
	WATER_GRASS3(22,true),
	WATER_GRASS4(23,true),
	WATER_GRASS5(24,true),
	WATER_GRASS6(25,true),
	WATER_GRASS7(26,true),
	WATER_GRASS8(27,true),
	WATER_GRASS9(28,true),
	WATER_GRASS10(29,true),
	WATER_GRASS11(30,true),
	WATER_GRASS12(31,true),
	STONE1(32,false),
	HUT1(33,false),
	WOOD1(34,false),
	TABLE1(35,true),
	DUNGEON_ENTRANCE1(36,false),
	DUNGEON_EXIT1(37,false),
	WATERSWIRL1(38,false),
	DOCK_WATER1(39,true),
	DOCK_WATER2(40,true),
	DOCK_WATER3(41,true),
	DOCK_WATER4(42,true),
	DOCK_WATER5(43,true),
	DOCK_WATER6(44,true),
	DOCK_WATER7(45,true),
	DOCK_WATER8(46,true),
	DOCK_WATER9(47,true),
	DOCK_WATER10(48,true),
	DOCK_WATER11(49,true),
	DOCK_WATER12(50,true),
	DOCK_GRASS1(51,true),
	DOCK_GRASS2(52,false),
	DOCK_GRASS3(53,true),
	DOCK_GRASS4(54,true),
	DOCK_GRASS5(55,false),
	DOCK_GRASS6(56,true),
	DOCK_GRASS7(57,true),
	DOCK_GRASS8(58,false),
	DOCK_GRASS9(59,true),
	DOCK_GRASS10(60,true),
	DOCK_GRASS11(61,false),
	DOCK_GRASS12(62,true),
	TREE2(63,true),
	TREE3(64,true),
	TREE_GROUP1(65,true),
	TREE_GROUP2(66,true),
	TREE_GROUP3(67,true),
	TREE_GROUP4(68,true),
	TREE_GROUP5(69,true),
	TREE_GROUP6(70,true),
	TREE_GROUP7(71,true),
	TREE_GROUP8(72,true),
	TREE_GROUP9(73,true),
	SAND_WATTER1(74,true),
	SAND_WATTER2(75,true),
	SAND_WATTER3(76,true),
	SAND_WATTER4(77,true),
	SAND_WATTER5(78,true),
	SAND_WATTER6(79,true),
	SAND_WATTER7(80,true),
	SAND_WATTER8(81,true),
	SAND_WATTER9(82,true),
	SAND_WATTER10(83,true),
	SAND_WATTER11(84,true),
	SAND_WATTER12(85,true),
	;

	public static final int TILE_SIZE = 64;
	
	private int id;
	private boolean collidable;
	private String name;
	private float damage;
	
	private TileType(int id, boolean collidable, String name) {
		this(id,collidable,name,0);
	}
	
	private TileType(int id, boolean collidable) {
		this.id = id;
		this.collidable = collidable;
	}
	
	private TileType(int id, boolean collidable, String name, float damage) {
		this.id = id;
		this.collidable = collidable;
		this.name = name;
		this.damage = damage;
	}

	
	public int getId() {
		return id;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public String getName() {
		return name;
	}

	public float getDamage() {
		return damage;
	}
	
	
	private static HashMap<Integer, TileType> tileMap;
	
	static {
		tileMap = new HashMap<Integer, TileType>();
		for(TileType tileType : TileType.values()){
			tileMap.put(tileType.getId(), tileType);
		}
	}
	
	public static TileType getTileTypeById(int id) {
		return tileMap.get(id);
	}
	
	
}
