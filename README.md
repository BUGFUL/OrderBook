[![Build Status](https://travis-ci.com/BUGFUL/OrderBook.svg?branch=master)](https://travis-ci.com/github/BUGFUL/OrderBook)


![Architecture](https://github.com/BUGFUL/OrderBook/blob/feature/readme/src/main/resources/screenshots/main.png)

<h4>How it works</h4>
<ol>
    <li>App1 receives Orders (XML) via /save</li>
    <li>App1 publish to RabbitMQ exchange order message and it then routes to Queue</li>
    <li>App2 consumes order message and save it in in-memory db (H2)</li>
</ol>


<h4>Tasks</h4>
<p>Clone/Fork repos (OrderBook and OrderDB) and do the following tasks in your repo</p>
<ol>
    <li>Implement order amendment:
        <ol>
            <li>If any field (except ID and TradeId) is changed then increase order version and update status AMEND</li>
            <li>Save in db</li>
        </ol>
    </li>
    <li>Implement order cancellation:
        <ol>
            <li>Update order status to CANCEL and increase version</li>
            <li>Save in db</li>
        </ol>
    </li>
    <li>Add unit tests</li>
    <li>Add E2E tests</li>
    <li>Add coverage</li>
    <li>Build (CI) should be green (all test passed)</li>
</ol>

<h4>How to run it locally (Windows)</h4>
<ol>
    <li><a href="https://www.erlang.org/downloads">Install Erlang OTP</a> before installing RabbitMQ broker/server
    </li>
    <li><a href="https://www.rabbitmq.com/install-windows.html#installer">Install RabbitMQ</a></li>
    </li>
    <li>Get up RabbitMQ locally via cmd under admin
        <ol>
            <li>rabbitmq-service install</li>
            <li>rabbitmqctl status</li>
            <li>rabbitmq-plugins enable rabbitmq_management</li>
            <li>rabbitmq-service stop</li>
            <li>rabbitmq-service start</li>
        </ol>
    </li>
    <li>Check via http://localhost:15672/ that your Rabbit server is up
        <ol>
            <li>Create new exchange "jsa.fanout" (see OrderBook application.yml)</li>
            <li>Create new queue "jsa.queue.1" (see OrderDB application.yml)</li>
            <li>Bind exchange and queue (e.g. http://localhost:15672/#/queues/%2F/jsa.queue.1)</li>
        </ol>
    </li>
    <li>Start OrderBook and then OrderDB locally></li>
    <li>You test end points via SOAP UI / PostMan</li>    
</ol>
