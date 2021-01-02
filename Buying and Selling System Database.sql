DROP DATABASE buyingAndSelling;
CREATE DATABASE buyingAndSelling;
USE buyingAndSelling;
CREATE TABLE employee(
	empId VARCHAR(6) NOT NULL,
	name VARCHAR(100) NOT NULL,
	address VARCHAR(200) NOT NULL,
	phone INT,
	NIC BIGINT(12),
	joinedDate DATE NOT NULL,
	password VARCHAR(25),
	authority ENUM ('ADMIN','USER') NOT NULL,
	CONSTRAINT PRIMARY KEY(empId)
);
CREATE TABLE supplier(
	supplierId VARCHAR(8) NOT NULL,
	firstname VARCHAR(100) NOT NULL,
	lastname VARCHAR(100) NOT NULL,
	address VARCHAR(200) NOT NULL,
	companyName VARCHAR(200),
	NIC BIGINT(12),
	gender ENUM ('Male','Female') NOT NULL,
	city VARCHAR(100) NOT NULL,
	mobile BIGINT(10), 
	email VARCHAR(200),
	postalCode VARCHAR(10),
	addedDate DATE NOT NULL,
	CONSTRAINT PRIMARY KEY(supplierId)
);
CREATE TABLE location(
	locationId VARCHAR(8) NOT NULL,
	rackNo VARCHAR(8),
	SectionName ENUM ('Foods','Electronics','Other') NOT NULL,
	CONSTRAINT PRIMARY KEY(locationId)
);

CREATE TABLE item(
	itemCode VARCHAR(8) NOT NULL,
	locationId VARCHAR(8) NOT NULL,
	description VARCHAR(200) NOT NULL,
	Category ENUM ('Foods','Computer & Accessories','Cell Phone & Accessories','Other') NOT NULL,
	CONSTRAINT PRIMARY KEY(itemCode),
	CONSTRAINT FOREIGN KEY(locationId) REFERENCES location(locationId)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE batch(
	batchNo VARCHAR(8) NOT NULL,
	CONSTRAINT PRIMARY KEY(batchNo)
);
CREATE TABLE batchDetail(
	batchNo VARCHAR(8) NOT NULL,
	itemCode VARCHAR(8) NOT NULL,
	unitPrice DECIMAL(15,2) NOT NULL,
	sellingPrice DECIMAL(15,2) NOT NULL,
	QTY DECIMAL NOT NULL,
	qtyOnHand DECIMAL,
	MFD DATE,
	EXP DATE,
	CONSTRAINT PRIMARY KEY(itemCode,batchNo),
	CONSTRAINT FOREIGN KEY(itemCode) REFERENCES item(itemCode)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(batchNo) REFERENCES batch(batchNo)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE purchaseStock(
	purchaseId VARCHAR(8) NOT NULL,
	supplierId VARCHAR(8) NOT NULL,
	purchaseDate DATE NOT NULL,
	CONSTRAINT PRIMARY KEY(purchaseId),
	CONSTRAINT FOREIGN KEY(supplierId) REFERENCES supplier(supplierId)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE purchaseStockDetail(
	purchaseId VARCHAR(8) NOT NULL,
	batchNo VARCHAR(8) NOT NULL,
	QTY DECIMAL NOT NULL,
	CONSTRAINT FOREIGN KEY(purchaseId) REFERENCES purchaseStock(purchaseId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(batchNo) REFERENCES batch(batchNo)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE customer(
	customerId VARCHAR(8) NOT NULL,
	firstname VARCHAR(100) NOT NULL,
	lastname VARCHAR(100) NOT NULL,
	address VARCHAR(200) NOT NULL,
	companyName VARCHAR(200),
	NIC BIGINT(12) NOT NULL,
	gender ENUM ('Male','Female') NOT NULL,
	city VARCHAR(100) NOT NULL,
	mobile BIGINT(10) NOT NULL,
	email VARCHAR(200),
	postalCode VARCHAR(10),
	addedDate DATE NOT NULL,
	CONSTRAINT PRIMARY KEY (customerId)
);

CREATE TABLE customerOrder(
	orderId VARCHAR(8) NOT NULL,
	customerId VARCHAR(8) NOT NULL,
	orderedDate DATE NOT NULL,
	orderedTime TIME NOT NULL,
	CONSTRAINT PRIMARY KEY (orderId),
	CONSTRAINT FOREIGN KEY(customerId) REFERENCES customer(customerId)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE customerOrderDetail(
	orderId VARCHAR(8) NOT NULL,
	batchNo VARCHAR(8) NOT NULL,
	itemCode VARCHAR(8) NOT NULL,
	QTY DECIMAL NOT NULL,
	CONSTRAINT FOREIGN KEY(orderId) REFERENCES customerOrder(orderId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(batchNo) REFERENCES batchDetail(batchNo)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY(itemCode) REFERENCES batchDetail(itemCode)
	ON UPDATE CASCADE ON DELETE CASCADE	
);
CREATE TABLE issuedCheque(
	issuedChequeId VARCHAR(100) NOT NULL,
	bankName VARCHAR(40) NOT NULL,
	amount DECIMAL(15,2) NOT NULL,
	status VARCHAR(100),
	issuedDate DATE,
	CONSTRAINT PRIMARY KEY (issuedChequeId)
); 
CREATE TABLE supplierPayment(
	paymentId VARCHAR(8) NOT NULL,
	purchaseId VARCHAR(8) NOT NULL,
	paidAmount DECIMAL(15,2) NOT NULL,
	totalAmount DECIMAL(15,2) NOT NULL,
	paymentMethod ENUM('Cheque','Cash') NOT NULL,
	issuedChequeId VARCHAR(100),
	paidDate DATE,
	CONSTRAINT PRIMARY KEY (paymentId),
	CONSTRAINT FOREIGN KEY(purchaseId) REFERENCES purchaseStock(purchaseId)
	ON UPDATE CASCADE ON DELETE CASCADE,	
	CONSTRAINT FOREIGN KEY (issuedChequeId) REFERENCES issuedCheque(issuedChequeId)
	ON UPDATE CASCADE ON DELETE CASCADE	
);
 
CREATE TABLE customerPayment(
	paymentId VARCHAR(8) NOT NULL,
	orderId VARCHAR(8) NOT NULL,
	amount DECIMAL(15,2) NOT NULL,
	paymentMethod ENUM('Cheque','Card','Cash') NOT NULL,
	recivedChaqueId VARCHAR(100),
	cardNo BIGINT(16),
	paidDate DATE NOT NULL,
	paidTime TIME NOT NULL,
	CONSTRAINT PRIMARY KEY (paymentId),
	CONSTRAINT FOREIGN KEY(orderId) REFERENCES customerOrder(orderId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (recivedChaqueId) REFERENCES recivedCheque(recivedChequeId)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (cardNo) REFERENCES card(cardNo)
	ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE recivedCheque(
	recivedChequeId VARCHAR(100) NOT NULL,
	bankName VARCHAR(40) NOT NULL,
	status VARCHAR(100),
	amount DECIMAL(15,2) NOT NULL,
	realizationDate DATE,
	recivedDate DATE,
	CONSTRAINT PRIMARY KEY (recivedChequeId)
);
CREATE TABLE card(
	cardNo BIGINT(16) NOT NULL,
	validDate DATE NOT NULL,
	CVV INT(3) NOT NULL,
	cardType ENUM('VISA','MASTER CARD','AMERICAN EXPRESS') NOT NULL,
	bankName VARCHAR(40) NOT NULL,
	status VARCHAR(100),
	amount DECIMAL(15,2) NOT NULL,
	recivedDate DATE NOT NULL,
	CONSTRAINT PRIMARY KEY (cardNo,recivedDate)
); 