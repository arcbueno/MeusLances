const express = require('express');
const router = express.Router();
const { check } = require('express-validator');
const productController = require('../src/controllers/product-controller');

router.get('/', productController.listProduct);
router.post('/', [check('name').isLength({ min: 5 }).withMessage('Required name parameter')], productController.createProduct);
router.put('/', productController.updateProduct);
router.delete('/:id', productController.deleteProduct);

module.exports = router;