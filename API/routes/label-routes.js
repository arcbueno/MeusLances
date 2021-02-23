const express = require('express');
const router = express.Router();
const { check } = require('express-validator');
const labelController = require('../src/controllers/label-controller');

router.get('/', labelController.listLabel);
router.post('/', [check('name').isLength({ min: 5 }).withMessage('Required name parameter')], labelController.createLabel);
router.put('/', labelController.updateLabel);
router.delete('/:id', labelController.deleteLabel);

module.exports = router;