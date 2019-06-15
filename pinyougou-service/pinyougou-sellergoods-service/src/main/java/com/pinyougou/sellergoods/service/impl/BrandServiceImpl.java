package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
@Service(interfaceName = "com.pinyougou.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService {
    /*注入数据访问接口代理对象*/
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void save(Brand brand) {
        brandMapper.insertSelective(brand);

    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {
        /*创建示范对象*/
        Example example=new Example(Brand.class);
        /*创建条件对象*/
        Example.Criteria criteria=example.createCriteria();
        /*添加in条件*/
        criteria.andIn("id", Arrays.asList(ids));
        /*根据条件删除*/
        brandMapper.deleteByExample(example);

    }

    @Override
    public Brand findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Brand> findAll(Brand brand) {
        //return brandMapper.findAll();

        /*开始分页*/
        PageInfo<Brand> pageInfo= PageHelper.startPage(1,10)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        brandMapper.selectAll();
                    }
                });
        System.out.println("总记录数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        return pageInfo.getList();
    }

    @Override
    public List<Brand> findAll() {
        return null;
    }

    @Override
    public PageResult findByPage(Brand brand, int page, int rows) {
        try {
            /*开始分页*/
            PageInfo<Brand> pageInfo = PageHelper.startPage(page,rows)
                    .doSelectPageInfo(new ISelect() {
                        @Override
                        public void doSelect() {
                            brandMapper.findAll(brand);
                        }
                    });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
