const express = require('express');
const router = express.Router();
const { check } = require('express-validator');
const orderController = require('../src/controllers/order-controller');

router.get('/', orderController.listOrder);
router.post('/', [check('productIdList').isLength({ min: 1 }).withMessage('Cannot create empty order')], orderController.createOrder);
router.put('/', orderController.updateOrder);
router.delete('/:id', orderController.deleteOrder);

module.exports = router;