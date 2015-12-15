QUnit.test("namespaces defined", function(assert) {
	assert.ok(typeof jts !== 'undefined', "jts defined");
	assert.ok(typeof jts.geom !== 'undefined', "jts.geom defined");
});

QUnit.test("jts.JTSVersion", function(assert) {
	assert.ok(typeof jts.JTSVersion !== 'undefined', "jts.JTSVersion defined");
	assert.ok(typeof jts.JTSVersion.CURRENT_VERSION !== 'undefined', "jts.JTSVersion.CURRENT_VERSION defined");
	assert.ok(typeof jts.JTSVersion.CURRENT_VERSION.getMajor() === 'number', "jts.JTSVersion.CURRENT_VERSION.getMajor() returns number");
	assert.ok(typeof jts.JTSVersion.CURRENT_VERSION.getMinor() === 'number', "jts.JTSVersion.CURRENT_VERSION.getMinor() returns number");
	assert.ok(typeof jts.JTSVersion.CURRENT_VERSION.getPatch() === 'number', "jts.JTSVersion.CURRENT_VERSION.getPatch() returns number");
	assert.ok(typeof jts.JTSVersion.CURRENT_VERSION.toString() === 'string', "jts.JTSVersion.CURRENT_VERSION.toString() returns string");
});

QUnit.test("jts.geom.Coordinate", function(assert) {
	assert.ok(typeof jts.geom.Coordinate !== 'undefined', "jts.geom.Coordinate defined");
	assert.ok(jts.geom.Coordinate.X === 0, "jts.geom.Coordinate.X == 0");
	assert.ok(jts.geom.Coordinate.Y === 1, "jts.geom.Coordinate.Y == 1");
	assert.ok(jts.geom.Coordinate.Z === 2, "jts.geom.Coordinate.Z == 2");
	
	var c = new jts.geom.Coordinate(1,2,3);
	assert.ok(c.x === 1, "new Coordinate(x, ...) and Coordinate.x is correct");
	assert.ok(c.y === 2, "new Coordinate(..., y, ...) and Coordinate.y is correct");
	assert.ok(c.z === 3, "new Coordinate(..., z) and Coordinate.z is correct");
});

QUnit.test("jts.geom.Envelope", function(assert) {
	assert.ok(typeof jts.geom.Envelope !== 'undefined', "jts.geom.Envelope defined");
});