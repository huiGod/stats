package cn._51app.stats.domain;

public class DeviceDay {
	
	private int id;

	private String uuid;
	
	private int cid;

	private int model;

	private int ios;

	private int version;
	
	private int _new;
	
	private int _1dayRetention;
	
	private int _2dayRetention;
	
	private int _3dayRetention;
	
	private int _7dayRetention;
	
	private int _14dayRetention;
	
	private int _30dayRetention;
	
	private int active;
	
	// 1,铃声2,壁纸3,表情
	private int type;
	
	private String createDay;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getIos() {
		return ios;
	}

	public void setIos(int ios) {
		this.ios = ios;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getType() {
		return type;
	}

	public int get_new() {
		return _new;
	}

	public void set_new(int _new) {
		this._new = _new;
	}

	public int get_1dayRetention() {
		return _1dayRetention;
	}

	public void set_1dayRetention(int _1dayRetention) {
		this._1dayRetention = _1dayRetention;
	}

	public int get_2dayRetention() {
		return _2dayRetention;
	}

	public void set_2dayRetention(int _2dayRetention) {
		this._2dayRetention = _2dayRetention;
	}

	public int get_3dayRetention() {
		return _3dayRetention;
	}

	public void set_3dayRetention(int _3dayRetention) {
		this._3dayRetention = _3dayRetention;
	}

	public int get_7dayRetention() {
		return _7dayRetention;
	}

	public void set_7dayRetention(int _7dayRetention) {
		this._7dayRetention = _7dayRetention;
	}

	public int get_14dayRetention() {
		return _14dayRetention;
	}

	public void set_14dayRetention(int _14dayRetention) {
		this._14dayRetention = _14dayRetention;
	}

	public int get_30dayRetention() {
		return _30dayRetention;
	}

	public void set_30dayRetention(int _30dayRetention) {
		this._30dayRetention = _30dayRetention;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	
}
